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


public class Captura {

	/**
	 * Main startup method
	 *
	 * @param args
	 *          ignored
	 */
   private static String asString(byte[] mac) {
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

  private static String ipString(byte[] ip){
  	StringBuilder buf = new StringBuilder();
  	for(byte b : ip){
  		if(buf.length() != 0){
  			buf.append(".");
  		}
  		buf.append(Integer.toString((b < 0) ? b + 256 : b));
  	}
  	return buf.toString();
  }

   private static long calculateChecksum(byte[] buf) {
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
		StringBuilder errbuf = new StringBuilder(); // For any error msgs

		/***************************************************************************
		 * First get a list of devices on this system
		 **************************************************************************/
		int r = Pcap.findAllDevs(alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
			System.err.printf("Can't read list of devices, error is %s", errbuf.toString());
			return;
		}

		System.out.println("Network devices found:");

		int i = 0;
		for (PcapIf device : alldevs) {
			try{
				String description = (device.getDescription() != null) ? device.getDescription() : "No description available";
				final byte[] mac = device.getHardwareAddress();
				String dir_mac = (mac == null) ? "There's no MAC address" : asString(mac);
				System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);
			}catch(IOException e){
				e.printStackTrace();
			}
		}

		System.out.println("\nChoose one device: ");
		int choice = sc.nextInt();
		System.out.println("");
		PcapIf device = alldevs.get(choice); // We know we have atleast 1 device

		/***************************************************************************
		 * Second we open up the selected device
		 **************************************************************************/
        /*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
        64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam máx de trama */

		int snaplen = 64 * 1024;           // Capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
		int timeout = 10 * 1000;           // 10 seconds in millis
        Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

		if (pcap == null) {
			System.err.printf("Error while opening device for capture: " + errbuf.toString());
			return;
		}

        /********F I L T R O********/
        PcapBpfProgram filter = new PcapBpfProgram();
        String expression = ""; // "port 80";
        int optimize = 0; // 1 means true, 0 means false
        int netmask = 0;
        int r2 = pcap.compile(filter, expression, optimize, netmask);
        if (r2 != Pcap.OK) {
            System.out.println("Filter error: " + pcap.getErr());
        }
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
					/******Desencapsulado********/
                    for(int i = 0; i < packet.size(); i++){
						System.out.printf("%02X ", packet.getUByte(i));
						if(i % 2 == 1)
							System.out.printf(" ");
						if(i % 16 == 15)
							System.out.println("");
                    }
                    System.out.println("\n\n");
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
		pcap.loop(50, jpacketHandler, "");

		/***************************************************************************
		 * Last thing to do is close the pcap handle
		 **************************************************************************/
		pcap.close();
	}
}