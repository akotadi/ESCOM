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


public class Practica03 {

	/**
	 * Main startup method
	 *
	 * @param args
	 *          ignored
	 */
   private static String asString(final byte[] mac) {
    final StringBuilder buf = new StringBuilder();
    for (byte b : mac) {
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

    public static String CodigoS(int clave){
        HashMap <Integer,String> codigo = new HashMap <Integer,String>();
        codigo.put(0,"Listo para recibir (RR)");
        codigo.put(1,"Rechazado (REJ)");
        codigo.put(2,"No listo para recibir (RNR)");
        codigo.put(3,"Rechazo selectivo (SREJ)");
        if (codigo.containsKey(clave)) {
            return codigo.get(clave);
        }
        else{
            return "";
        }
    }

    public static String ComandoU(int clave){
        HashMap <Integer,String> codigo = new HashMap <Integer,String>();
        codigo.put(0, "Información sin numerar (UI)");
        codigo.put(1, "Activar modo de inicializacion (SIM)");
        codigo.put(3, "Activación modo de respuesta asíncrona(SARM)");
        // Relación M-E esclavo transmite sin pedir permiso
        codigo.put(4, "Sondeo sin numero (UP)");
        codigo.put(7, "Activación de modo respuesta asíncrono balanceado (SABM)");
        // Relación combinada
        codigo.put(8, "Desconexion (DISC)");
        codigo.put(11, "Activación modo de respuesta asíncrono extendido (SARME)");
        codigo.put(15, "Activación de modo respuesta asincrono balanceado extendido (SABME)");
        codigo.put(16, "Activación de modo respuesta normal (SNRM)");
        // Relación M-E esclavo solicita permiso para transmitir
        codigo.put(17, "Rechazo de trama (FRMR");
        codigo.put(19, "Reinicio (RSET)");
        codigo.put(23, "Intercambio de ID (XID)");
        codigo.put(27, "Activación de modo respuesta normal, extendida (SNRME)");
        if (codigo.containsKey(clave)) {
            return codigo.get(clave);
        }
        else{
            return "";
        }
    }

    public static String RespuestaU(int clave){
        HashMap <Integer,String> codigo = new HashMap <Integer,String>();
        codigo.put(0, "Informacion sin numerar (UI)");
        codigo.put(1, "Solicitar modo de Informacion (RIM)");
        codigo.put(3, "Modo desconectado (DM)");
        codigo.put(8, "Solicitar desconexion (RD)");
        codigo.put(12, "Reconocimiento sin numerar (UA)");
        codigo.put(17, "Rechazo de trama (FRMR)");
        codigo.put(23, "Intercambio de ID (XID)");
        if (codigo.containsKey(clave)) {
            return codigo.get(clave);
        }
        else{
            return "";
        }
    }

	public static void main(String[] args) {
            Pcap pcap=null;
               try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
		StringBuilder errbuf = new StringBuilder(); // For any error msgs
                System.out.println("[0]-->Realizar captura de paquetes al vuelo");
                System.out.println("[1]-->Cargar traza de captura desde archivo");
                System.out.print("\nElige una de las opciones:");
                int opcion = Integer.parseInt(br.readLine());
                if (opcion==1){
                    
                    /////////////////////////lee archivo//////////////////////////
                //String fname = "archivo.pcap";
                String fname = "paquetes3.pcap";
                pcap = Pcap.openOffline(fname, errbuf);
                if (pcap == null) {
                  System.err.printf("Error while opening device for capture: "+ errbuf.toString());
                  return;
                 }//if
                } else if(opcion==0){
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
		for (PcapIf device : alldevs) {
			String description =
			    (device.getDescription() != null) ? device.getDescription()
			        : "No description available";
                        final byte[] mac = device.getHardwareAddress();
			String dir_mac = (mac==null)?"No tiene direccion MAC":asString(mac);
                        System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);
                        List<PcapAddr> direcciones = device.getAddresses();
                        for(PcapAddr direccion:direcciones){
                            System.out.println(direccion.getAddr().toString());
                        }//foreach

		}//for
                
                System.out.print("\nEscribe el número de interfaz a utilizar:");
                int interfaz = Integer.parseInt(br.readLine());
		PcapIf device = alldevs.get(interfaz); // We know we have atleast 1 device
		System.out
		    .printf("\nChoosing '%s' on your behalf:\n",
		        (device.getDescription() != null) ? device.getDescription()
		            : device.getName());
                
		/***************************************************************************
		 * Second we open up the selected device
		 **************************************************************************/
                /*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
                64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam máx de trama */

		int snaplen = 64 * 1024;           // Capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
		int timeout = 10 * 1000;           // 10 seconds in millis

                
                pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

		if (pcap == null) {
			System.err.printf("Error while opening device for capture: "
			    + errbuf.toString());
			return;
		}//if
                  
                       /********F I L T R O********/
            PcapBpfProgram filter = new PcapBpfProgram();
            String expression =""; // "port 80";
            int optimize = 0; // 1 means true, 0 means false
            int netmask = 0;
            int r2 = pcap.compile(filter, expression, optimize, netmask);
            if (r2 != Pcap.OK) {
                System.out.println("Filter error: " + pcap.getErr());
            }//if
            pcap.setFilter(filter);
                /****************/
            }//else if

		/***************************************************************************
		 * Third we create a packet handler which will receive packets from the
		 * libpcap loop.
		 **********************************************************************/
		PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

			public void nextPacket(PcapPacket packet, String user) {

				System.out.printf("\n\nPaquete recibido el %s caplen=%-4d longitud=%-4d %s\n\n",
				    new Date(packet.getCaptureHeader().timestampInMillis()),
				    packet.getCaptureHeader().caplen(),  // Length actually captured
				    packet.getCaptureHeader().wirelen(), // Original length
				    user                                 // User supplied object
				    );
                                
                                
                                /******Desencapsulado********/
                                for(int i=0;i<packet.size();i++){
                                    System.out.printf("%02X ",packet.getUByte(i));
                                    if(i%16==15)
                                        System.out.println("");
                                }//if

                                int lon = packet.size();

                                byte[] trama = packet.getByteArray(0, lon);
                                
                                int longitud = (((trama[12] << 8) & 0xFF00) | ((trama[13]) & 0xFF));
                                System.out.printf("\nLongitud: %d (%04X)",longitud,longitud );
                                if(longitud<1500){
                                    System.out.println("--->Trama IEEE802.3");
                                    // Principalmente usadas en redes one
                                    System.out.printf(" |-->MAC Destino: %02X:%02X:%02X:%02X:%02X:%02X\n",trama[0],trama[1],trama[2],trama[3],trama[4],trama[5]);
                                    System.out.printf(" |-->MAC Origen: %02X:%02X:%02X:%02X:%02X:%02X\n",trama[6],trama[7],trama[8],trama[9],trama[10],trama[11]);
                                    byte bDSAP = trama[14];
                                    System.out.printf(" |-->DSAP: %02X ",bDSAP);
                                    if ((bDSAP & 0x01) == 0x00) {
                                        System.out.print("(Individual)\n");
                                    }
                                    else{
                                        System.out.print("(Grupal)\n");
                                    }
                                    byte bSSAP = trama[15];
                                    System.out.printf(" |-->SSAP: %02X ",bSSAP);
                                    if ((bSSAP & 0x01) == 0x00) {
                                        System.out.print("(Comando)\n");
                                    }
                                    else{
                                        System.out.print("(Respuesta)\n");
                                    }

                                    int codigo = 0, nNS = 0, nNR = 0, nPF = 0;

                                    if ((trama[16] & 0x01) == 0x00) {
                                        System.out.print(" |-->Trama I : ");
                                        // Sirven para enviar datos de aplicación
                                        if (longitud < 4) {
                                            // Un byte de campo de control
                                            System.out.println("Modo Normal");
                                            nNS = (trama[16] & 0x0E);
                                            nPF = (trama[16] & 0x10);
                                            nNR = ((trama[16] >> 5) & 0x07);
                                        }
                                        else{
                                            // Dos bytes de campo de control
                                            System.out.println("Modo Extendido");
                                            nNS = ((trama[16] & 0xFE) >> 1);
                                            nPF = (trama[17] & 0x01);
                                            nNR = ((trama[17] & 0xFE) >> 1);
                                        }
                                        System.out.println(" |-->N(S) = "+nNS);
                                        // Número de secuencia
                                        System.out.println(" |-->N(R) = "+nNR);
                                        // Número de acuse
                                        System.out.println(" |-->P/F = "+nPF);
                                    }
                                    else if ((trama[16] & 0x03) == 0x01) {
                                        System.out.print(" |-->Trama S : ");
                                        // Tramas de supervisión para control de error y control de flujo
                                        codigo = (trama[16] & 0x0C);
                                        if (longitud < 4) {
                                            // Un byte de campo de control
                                            System.out.println("Modo Normal");
                                            nPF = (trama[16] & 0x10);
                                            nNR = ((trama[16] >> 5) & 0x07);
                                        }
                                        else{
                                            // Dos bytes de campo de control
                                            System.out.println("Modo Extendido");
                                            nPF = (trama[17] & 0x01);
                                            nNR = ((trama[17] & 0xFE) >> 1);
                                        }
                                        System.out.println(" |-->Código = "+Integer.toBinaryString(codigo)+" : "+CodigoS(codigo));
                                        System.out.println(" |-->N(R) = "+nNR);
                                        System.out.println(" |-->P/F = "+nPF);
                                    }
                                    else if ((trama[16] & 0x03) == 0x03) {
                                        System.out.println(" |-->Trama U");
                                        // Las tramas no numeradas van a ser para configurar los canales de comunicación, siempre es un byte
                                        codigo = ((trama[16] >> 2) & 0x03) | ((trama[16] >> 3) & 0x1C);
                                        nPF = ((trama[16] >> 4) & 0x01);
                                        if ((bSSAP & 0x01) == 0x00) {
                                            System.out.println(" |-->Codigo = "+Integer.toBinaryString(codigo)+" : "+ComandoU(codigo));
                                        }
                                        else{
                                            System.out.println(" |-->Codigo = "+Integer.toBinaryString(codigo)+" : "+RespuestaU(codigo));
                                        }
                                        System.out.println(" |-->P/F = "+nPF);
                                    }
                                } else{
                                    System.out.println("-->Trama ETHERNET");
                                }//else
                                
                                
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
                }catch(IOException e){e.printStackTrace();}
	}
}
