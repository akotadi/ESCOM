import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.io.*;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapAddr;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.PcapBpfProgram;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.tcpip.*;
import org.jnetpcap.protocol.network.*;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.lan.IEEE802dot2;
import org.jnetpcap.protocol.lan.IEEE802dot3;

public class Captura2 {

	/**
	 * Main startup method
	 *
	 * @param args
	 *		  ignored
	 */
	private static String macString(final byte[] mac) {
		final StringBuilder buf = new StringBuilder();
		for (byte b: mac) {
			if (buf.length() != 0) {
				buf.append(':');
			}
			if (b >= 0 && b < 16) {
				buf.append('0');
			}
			buf.append(Integer.toHexString((b < 0) ? b + 256 : b).toUpperCase());
		}

		return buf.toString();
	}

	public static int uInt(byte b) {
		return ((int) b) & 0xFF;
	}

	public static String bin2str(int n) {
		return String.format("%5s", Integer.toBinaryString(n)).replace(' ', '0');
	}

	public static String codigoS(int codigo) {
		HashMap < Integer, String > codigos = new HashMap < Integer, String > ();
		codigos.put(0, "RR (listo para recibir)");
		codigos.put(1, "RNR (no listo para recibir)");
		codigos.put(2, "REI (rechazado)");
		codigos.put(3, "SREI (rechazo selectivo)");
		return codigos.get(codigo);
	}

	public static String codigoU(int codigo, int tipo) {
		HashMap < Integer, String > comandos = new HashMap < Integer, String > ();
		comandos.put(16, "SNRM (Activacion de modo respuesta normal)");
		comandos.put(27, "SNRME (Activacion de modo respuesta normal, extendida)");
		comandos.put(7, "SABM (Activacion de modo respuesta asincrona)");
		comandos.put(15, "SABME (Activacion de modo respuesta asincrona, extendida)");
		comandos.put(0, "UI (Informacion sin numerar)");
		comandos.put(12, "");
		comandos.put(8, "DISC (Desconexion)");
		comandos.put(1, "SIM (Activar modo de inicializacion)");
		comandos.put(4, "UP (Sondeo sin numero)");
		comandos.put(19, "RSET (Reinicio)");
		comandos.put(23, "XID (Intercambio de ID)");
		comandos.put(17, "FRMR (Rechazo de trama)");
		HashMap < Integer, String > respuestas = new HashMap < Integer, String > ();
		respuestas.put(16, "");
		respuestas.put(27, "");
		respuestas.put(7, "DM (Modo desconectado)");
		respuestas.put(15, "");
		respuestas.put(0, "UI (Informacion sin numerar)");
		respuestas.put(12, "UA (Reconocimiento sin numerar)");
		respuestas.put(8, "RD (Solicitar desconexion)");
		respuestas.put(1, "RIM (Solicitar modo de Informacion)");
		respuestas.put(4, "");
		respuestas.put(19, "");
		respuestas.put(23, "XID (Intercambio de ID)");
		respuestas.put(17, "FRMR (Rechazo de trama)");
		if (tipo == 0) { //comando
			return comandos.get(codigo);
		} else { //respuesta
			return respuestas.get(codigo);
		}
	}

	public static void main(String[] args) {
		Pcap pcap = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			List < PcapIf > alldevs = new ArrayList < PcapIf > (); // Will be filled with NICs
			StringBuilder errbuf = new StringBuilder(); // For any error msgs
			System.out.println("[0]-->Realizar captura de paquetes al vuelo");
			System.out.println("[1]-->Cargar traza de captura desde archivo");
			System.out.print("\nElige una de las opciones:");
			int opcion = Integer.parseInt(br.readLine());
			if (opcion == 1) {

				/////////////////////////lee archivo//////////////////////////
				//String fname = "archivo.pcap";
				String fname = "paquetes3.pcap";
				pcap = Pcap.openOffline(fname, errbuf);
				if (pcap == null) {
					System.err.printf("Error while opening device for capture: " + errbuf.toString());
					return;
				} //if
			} else if (opcion == 0) {
				/***************************************************************************
				 * First get a list of devices on this system
				 **************************************************************************/
				int r = Pcap.findAllDevs(alldevs, errbuf);
				if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
					System.err.printf("Can't read list of devices, error is %s", errbuf
						.toString());
					return;
				}

				System.out.println("Network devices found:");

				int i = 0;
				for (PcapIf device: alldevs) {
					String description =
						(device.getDescription() != null) ? device.getDescription() :
						"No description available";
					final byte[] mac = device.getHardwareAddress();
					String dir_mac = (mac == null) ? "No tiene direccion MAC" : macString(mac);
					System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);
					List < PcapAddr > direcciones = device.getAddresses();
					for (PcapAddr direccion: direcciones) {
						System.out.println(direccion.getAddr().toString());
					} //foreach

				} //for

				System.out.print("\nEscribe el número de interfaz a utilizar:");
				int interfaz = Integer.parseInt(br.readLine());
				PcapIf device = alldevs.get(interfaz); // We know we have atleast 1 device
				System.out
					.printf("\nChoosing '%s' on your behalf:\n",
						(device.getDescription() != null) ? device.getDescription() :
						device.getName());

				/***************************************************************************
				 * Second we open up the selected device
				 **************************************************************************/
				/*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
				64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam máx de trama */

				int snaplen = 64 * 1024; // Capture all packets, no trucation
				int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
				int timeout = 10 * 1000; // 10 seconds in millis


				pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

				if (pcap == null) {
					System.err.printf("Error while opening device for capture: " +
						errbuf.toString());
					return;
				} //if

				/********F I L T R O********/
				PcapBpfProgram filter = new PcapBpfProgram();
				String expression = ""; // "port 80";
				int optimize = 0; // 1 means true, 0 means false
				int netmask = 0;
				int r2 = pcap.compile(filter, expression, optimize, netmask);
				if (r2 != Pcap.OK) {
					System.out.println("Filter error: " + pcap.getErr());
				} //if
				pcap.setFilter(filter);
				/****************/
			} //else if

			/***************************************************************************
			 * Third we create a packet handler which will receive packets from the
			 * libpcap loop.
			 **********************************************************************/
			PcapPacketHandler < String > jpacketHandler = new PcapPacketHandler < String > () {

				public void nextPacket(PcapPacket packet, String user) {

					System.out.printf("\n\nPaquete recibido el %s caplen=%-4d longitud=%-4d %s\n\n",
						new Date(packet.getCaptureHeader().timestampInMillis()),
						packet.getCaptureHeader().caplen(), // Length actually captured
						packet.getCaptureHeader().wirelen(), // Original length
						user // User supplied object
					);

					int len = packet.size();
					byte[] trama = packet.getByteArray(0, len);

					/******Desencapsulado********/
					for (int i = 0; i < len; i++) {
						System.out.printf("%02X ", trama[i]);

						if (i % 2 == 1)
							System.out.print(" ");
						if (i % 16 == 15)
							System.out.println("");
					}

					int longitud = (uInt(trama[12]) << 8) | uInt(trama[13]);
					System.out.printf("\nLongitud: %d (%04X)", longitud, longitud);
					if (longitud < 1500) {
						System.out.println("--->Trama IEEE802.3");
						byte[] macOrigen = new byte[6];
						byte[] macDestino = new byte[6];
						System.arraycopy(trama, 6, macOrigen, 0, 6);
						System.arraycopy(trama, 0, macDestino, 0, 6);
						System.out.println(" |-->MAC Destino: " + macString(macDestino));
						System.out.println(" |-->MAC Origen: " + macString(macOrigen));
						byte dsap = trama[14];
						System.out.printf(" |-->DSAP: %02X ", dsap);
						if ((dsap & 0x01) == 0x00) System.out.println("individual");
						else System.out.println("grupo");
						byte ssap = trama[15];
						System.out.printf(" |-->SSAP: %02X ", ssap);
						if ((ssap & 0x01) == 0x00) System.out.println("comando");
						else System.out.println("respuesta");

						int n_r, n_s, p_f, codigo;
						if ((trama[16] & 0x01) == 0) { //I frame
							System.out.println(" |-->Trama I");
							if (longitud <= 3) {
								System.out.println(" |-->1 byte (normal)");
								n_s = (trama[16] >> 1) & 0x07;
								p_f = (trama[16] >> 4) & 0x01;
								n_r = (trama[16] >> 5) & 0x07;
							} else {
								System.out.println(" |-->2 bytes (extendido)");
								n_s = (trama[16] >> 1) & 0x7F;
								p_f = trama[17] & 0x01;
								n_r = (trama[17] >> 1) & 0x7F;
							}
							System.out.println(" |-->N(R) = " + n_r + "\n |-->N(S) = " + n_s + "\n |-->P/F = " + p_f);
						} else if ((trama[16] & 0x03) == 0x01) { //S frame
							System.out.println(" |-->Trama S");
							if (longitud <= 3) {
								System.out.println(" |-->1 byte");
								codigo = (trama[16] >> 2) & 0x03;
								p_f = (trama[16] >> 4) & 0x01;
								n_r = (trama[16] >> 5) & 0x07;
							} else {
								System.out.println(" |-->2 bytes");
								codigo = (trama[16] >> 2) & 0x03;
								p_f = trama[17] & 0x01;
								n_r = (trama[17] >> 1) & 0x7F;
							}
							System.out.println(" |-->Codigo = " + bin2str(codigo) + ": " + codigoS(codigo) + "\n |-->N(R) = " + n_r + "\n |-->P/F = " + p_f);
						} else if ((trama[16] & 0x03) == 0x03) { //U frame
							System.out.println(" |-->Trama U");
							p_f = (trama[16] >> 4) & 0x01;
							codigo = ((trama[16] >> 3) & 0x1C) | ((trama[16] >> 2) & 0x03);
							System.out.printf("%d - %s\n",0x1C,Integer.toBinaryString(0x1C));
							System.out.printf(" |-->Codigo = %s: %s\n |-->P/F = %d\n",Integer.toBinaryString(codigo),codigoU(codigo, ssap & 0x01),p_f);
							//System.out.println(" |-->Codigo = " + codigo + ": " + codigoU(codigo, ssap & 0x01) + "\n |-->P/F = " + p_f);
						}

					} else if (longitud >= 1500) {
						System.out.println("-->Trama ETHERNET");
					} //else


					//System.out.println("\n\nEncabezado: "+ packet.toHexdump());


				}
			};


			/***************************************************************************
			 * Fourth we enter the loop and tell it to capture 10 packets. The loop
			 * method does a mapping of pcap.datalink() DLT value to JProtocol ID, which
			 * is needed by JScanner. The scanner scans the packet buffer and decodes
			 * the headers. The mapping is done automatically, although a variation on
			 * the loop method exists that allows the programmer to sepecify exactly
			 * which protocol ID to use as the data link type for this pcap interface.
			 **************************************************************************/
			pcap.loop(-1, jpacketHandler, " ");

			/***************************************************************************
			 * Last thing to do is close the pcap handle
			 **************************************************************************/
			pcap.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
