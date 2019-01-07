import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;
import java.util.*;

import org.jnetpcap.Pcap;
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

    public static long calculateChecksum(byte[] buf) {
    int length = buf.length;
    int i = 0;

    long sum = 0;
    long data;

    // Handle all pairs
    while (length > 1) {
      // Corrected to include @Andy's edits and various comments on Stack Overflow
      data = (((buf[i] << 8) & 0xFF00) | ((buf[i + 1]) & 0xFF));
      sum += data;
      // 1's complement carry bit correction in 16-bits (detecting sign extension)
      if ((sum & 0xFFFF0000) > 0) {
        sum = sum & 0xFFFF;
        sum += 1;
      }

      i += 2;
      length -= 2;
    }

    // Handle remaining byte in odd length buffers
    if (length > 0) {
      // Corrected to include @Andy's edits and various comments on Stack Overflow
      sum += (buf[i] << 8 & 0xFF00);
      // 1's complement carry bit correction in 16-bits (detecting sign extension)
      if ((sum & 0xFFFF0000) > 0) {
        sum = sum & 0xFFFF;
        sum += 1;
      }
    }

    // Final 1's complement value correction to 16-bits
    sum = ~sum;
    sum = sum & 0xFFFF;
    return sum;

  }

	public static void CheckSum(PcapPacket packet, int size){
		long suma = 0;
		long Datos[] = new long[size/2];
		for (int i = 0;i<size/2;i++) {
			Datos[i] = (((packet.getUByte(14+(i*2))<<8) & 0xFF00) | (packet.getUByte(14+(i*2)+1) & 0x00FF));
			suma += ((packet.getUByte(14+(i*2))<<8) & 0xFF00) | (packet.getUByte(14+(i*2)+1) & 0x00FF);
			//System.out.println(Datos[i]);
		}
		while ((suma & 0xFFFF0000) != 0) {
			long aux = ((suma & 0xFFFF0000)>>16);
			suma = (suma & 0xFFFF);
			suma += aux;
		}

		//System.out.printf("%02X ",suma);
		//System.out.println("");
		suma = ~suma;
		suma = (suma & 0xFFFF);
		System.out.printf("%02X ",suma);
		System.out.println("");
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
		StringBuilder errbuf = new StringBuilder(); // For any error msgs

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
                try{
		for (PcapIf device : alldevs) {
			String description =
			    (device.getDescription() != null) ? device.getDescription()
			        : "No description available";
                        final byte[] mac = device.getHardwareAddress();
			String dir_mac = (mac==null)?"No tiene direccion MAC":asString(mac);
                        System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);

		}//for

		System.out.println("\nChoose one device: ");
		int choice = sc.nextInt();
		System.out.println("");
		PcapIf device = alldevs.get(choice); // We know we have atleast 1 device
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
                Pcap pcap =
		    Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

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


		/***************************************************************************
		 * Third we create a packet handler which will receive packets from the
		 * libpcap loop.
		 **********************************************************************/
		PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

			public void nextPacket(PcapPacket packet, String user) {

				
				System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n",
				    new Date(packet.getCaptureHeader().timestampInMillis()),
				    packet.getCaptureHeader().caplen(),  // Length actually captured
				    packet.getCaptureHeader().wirelen(), // Original length
				    user                                 // User supplied object
				    );

				System.out.print("MAC Destino: ");
				for(int i=0;i<6;i++){
	                System.out.printf("%02X ",packet.getUByte(i));
	                if(i%16==15)
	                    System.out.println("");
                }
                System.out.print("\nMAC Origen: ");
                for(int i=6;i<12;i++){
	                System.out.printf("%02X ",packet.getUByte(i));
	                if(i%16==15)
	                    System.out.println("");
                }
                System.out.print("\nTipo: ");
                for(int i=12;i<14;i++){
	                System.out.printf("%02X ",packet.getUByte(i));
                }
                System.out.println("");

                int lon = packet.size();

                byte[] trama = packet.getByteArray(0, lon);
                //System.out.println(" ( Tamaño "+(4*(packet.getUByte(14)&0x0F))+" )");

                if (packet.size() > 12 && packet.getUByte(12) == 0x08 && packet.getUByte(13) == 00) {
                	System.out.println("Protocolo IP");
                	int lonIP = 4*(packet.getUByte(14)&0x0F);
               	 	byte[] encabezadoIP = new byte[lonIP];
               	 	System.arraycopy(trama, 14, encabezadoIP, 0, lonIP);
                	byte[] IP_origen = new byte[4];
                	System.arraycopy(encabezadoIP, 12, IP_origen, 0, 4); // De donde, inicio, a donde, inicio, fin de la copia
                	byte[] IP_destino = new byte[4];
                	System.arraycopy(encabezadoIP, 16, IP_destino, 0, 4);
                	System.out.printf("Checksum IP: %02X\n", calculateChecksum(encabezadoIP));
                	if (packet.size() > 23 && trama[23] == 0x06) {
                		System.out.println("Protocolo TCP");
						int lonTCP = (((trama[16] << 8) & 0xFF00) | ((trama[17]) & 0xFF));
						lonTCP -= lonIP;
						byte[] encabezadoTCP = new byte[lonTCP];
						System.arraycopy(trama, 14 + lonIP, encabezadoTCP, 0, lonTCP);
						byte[] pseudoEncabezado = new byte[12];
						// IP Origen + IP Destino + byte ceros + protocolo + longitud pdu transporte + pdu transporte
						System.arraycopy(IP_origen, 0, pseudoEncabezado, 0, 4); // IP Origen
						System.arraycopy(IP_destino, 0, pseudoEncabezado, 4, 4); // IP Destino
						pseudoEncabezado[8] = 0x00; // byte ceros
						pseudoEncabezado[9] = 0x06; // protocolo
						int lonTCP_payload = lon - 14 - lonIP; 
						pseudoEncabezado[10] = (byte)((lonTCP_payload & 0xFF00) >> 8);
						pseudoEncabezado[11] = (byte)(lonTCP_payload & 0xFF);
						int len_payload = lon - 14 - lonIP - lonTCP;
						byte[] payload = new byte[len_payload];
						System.arraycopy(trama, 14 + lonIP + lonTCP, payload, 0, len_payload);
						byte[] tmp = new byte[12 + lonTCP_payload];
						System.arraycopy(pseudoEncabezado, 0, tmp, 0, 12);
						System.arraycopy(encabezadoTCP, 0, tmp, 12, lonTCP);
						System.arraycopy(payload, 0, tmp, 12 + lonTCP, len_payload);
						System.out.printf("Checksum TCP: %02X\n", calculateChecksum(tmp));
					}else if ((packet.size() > 23) && (trama[23] == 0x11)) {
                		System.out.println("Protocolo UDP");
						int lonUDP = 8;
						byte[] encabezadoUDP = new byte[lonUDP];
						System.arraycopy(trama, 14 + lonIP, encabezadoUDP, 0, lonUDP);
						byte[] pseudoEncabezado = new byte[12];
						System.arraycopy(IP_origen, 0, pseudoEncabezado, 0, 4);
						System.arraycopy(IP_destino, 0, pseudoEncabezado, 4, 4);
						pseudoEncabezado[8] = 0x00;
						pseudoEncabezado[9] = 0x11;
						System.arraycopy(encabezadoUDP, 4, pseudoEncabezado, 10, 2);
						int len_payload = lon - 14 - lonIP - lonUDP;
						byte[] payload = new byte[len_payload];
						System.arraycopy(trama, 14 + lonIP + lonUDP, payload, 0, len_payload);
						byte[] tmp = new byte[12 + lonUDP + len_payload];
						System.arraycopy(pseudoEncabezado, 0, tmp, 0, 12);
						System.arraycopy(encabezadoUDP, 0, tmp, 12, lonUDP);
						System.arraycopy(payload, 0, tmp, 12 + lonUDP, len_payload);
						System.out.printf("Checksum UDP: %02X\n", calculateChecksum(tmp));
                	}
                }


// Campo 16 * 256 + 17
				System.out.print("\n");
				
                                /******Desencapsulado********/
                                for(int i=0;i<packet.size();i++){
                                System.out.printf("%02X ",packet.getUByte(i));
                                if(i%16==15)
                                    System.out.println("");
                                }
                                System.out.println("\n\nEncabezado: \n"+ packet.toHexdump());
      

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
		pcap.loop(10, jpacketHandler, "jNetPcap rocks!");

		/***************************************************************************
		 * Last thing to do is close the pcap handle
		 **************************************************************************/
		pcap.close();
                }catch(IOException e){e.printStackTrace();}
	}

}
