import java.io.*;
import java.util.Date;

class procesador{

	String headers;	
	Date ahora ;	
	String BUENO = "HTTP/1.1 200 OK\r\n";
	String NOENC = "HTTP/1.1 404 Not Found\r\n";
	String NOLEC = "HTTP/1.1 403 Forbidden\r\n";

	procesador(){
		ahora = new Date();	
		headers = "Date: "+ ahora + "\r\n"+
				  "Content-Type: text/html; charset=utf-8\r\n\r\n";
	}

	procesador(String headers){		

		this.headers = headers;
	}

	
	byte[] Get(String loc, String dir) throws Exception{		

		String props = "";

		int pos = 0;
		String aux = "";
		while(pos < loc.length() && loc.charAt(pos) != '?'){
			aux += loc.charAt(pos);
			pos ++;
		}
		
		pos ++;
		while(pos < loc.length()){
			props += loc.charAt(pos);
			pos++;
		}

		loc = aux;
		if(props.length() > 0) props = ", " + props;
		
		if(loc.equals("")) loc = "index.html";

		System.out.println(	"Petición GET: " + loc + props + ", origen: " + dir);

		String ret;
		File archivo = new File(loc);

		if(!archivo.exists()){
			ret = NOENC + headers; archivo = new File("NotFound.html");					
		} else if(!archivo.canRead()){
			ret = NOLEC + headers; archivo = new File("Forbidden.html");		
		} else { ret = BUENO + headers; }

		String tipo = archivo.getName();
		tipo = tipo.substring(tipo.lastIndexOf('.')+1);

		//System.out.println(tipo);
		//System.out.println(archivo.length());

		String linea;

		if(tipo.equals("html")){

			try{
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				while ((linea = br.readLine()) != null) 
					ret += linea + "\r\n";							
			} catch(Exception ex){
				System.out.println(ex.toString());
			}

			return ret.getBytes();	

		} else {
			if(tipo.equals("png")) ret = BUENO + "Date: "+ ahora + "\r\n"+ "Content-Type: image/png\r\n";
			else if(tipo.equals("jpeg") || tipo.equals("jpg")) ret = BUENO + "Date: "+ ahora + "\r\n"+ "Content-Type: image/jpeg\r\n";
			else if(tipo.equals("pdf")) ret = BUENO + "Date: "+ ahora + "\r\n"+ "Content-Type: application/pdf\r\n";
			else ret = BUENO + "Date: "+ ahora + "\r\n"+ "Content-Type: application/octet-stream\r\n";

			ret += "Content-Length: " + archivo.length() + "\r\n\r\n";

			byte[] prev = ret.getBytes();

			int sp = prev.length;
			int sf = (int)archivo.length();

			DataInputStream entrada = new DataInputStream(new FileInputStream(archivo));

			byte[] buff = new byte[sf];

			entrada.read(buff);

			byte[] res = new byte[sf+sp];

			for(int i = 0; i < sp; i ++)
				res[i] = prev[i];
			for(int i = 0; i < sf; i ++)
				res[i+sp] = buff[i];

			return res;



		}
		
	}

	byte[] Post(String loc, String props, String dir) throws Exception{		

		int pos = 0;
		String aux = "";
		while(pos < loc.length() && loc.charAt(pos) != '?'){
			aux += loc.charAt(pos);
			pos ++;
		}
		loc = aux;	
				
		if(props.length() > 0) props = ", " + props;
		
		if(loc.equals("")) loc = "index.html";

		System.out.println(	"Petición POST: " + loc + props + ", origen: " + dir);

		String ret;
		File archivo = new File(loc);

		if(!archivo.exists()){
			ret = NOENC + headers; archivo = new File("NotFound.html");					
		} else if(!archivo.canRead()){
			ret = NOLEC + headers; archivo = new File("Forbidden.html");		
		} else { ret = BUENO + headers; }

		String tipo = archivo.getName();
		tipo = tipo.substring(tipo.lastIndexOf('.')+1);

		System.out.println(tipo);
		System.out.println(archivo.length());

		String linea;

		if(tipo.equals("html")){

			try{
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				while ((linea = br.readLine()) != null) {
					if(linea.equals("\t\t<p1> Esta página apareció con una solicitud tipo <b>GET</b></p1>"))
						linea = "\t\t<p1> Esta página apareció con una solicitud tipo <b>POST</b></p1>";
					ret += linea + "\r\n";			
				}
				br.close();				
			} catch(Exception ex){
				System.out.println(ex.toString());
			}

			return ret.getBytes();

		} else {
			if(tipo.equals("png")) ret = BUENO + "Date: "+ ahora + "\r\n"+ "Content-Type: image/png\r\n";
			else if(tipo.equals("jpeg") || tipo.equals("jpg")) ret = BUENO + "Date: "+ ahora + "\r\n"+ "Content-Type: image/jpeg\r\n";
			else if(tipo.equals("pdf")) ret = BUENO + "Date: "+ ahora + "\r\n"+ "Content-Type: application/pdf\r\n";
			else ret = BUENO + "Date: "+ ahora + "\r\n"+ "Content-Type: application/octet-stream\r\n";

			ret += "Content-Length: " + archivo.length() + "\r\n\r\n";

			byte[] prev = ret.getBytes();

			int sp = prev.length;
			int sf = (int)archivo.length();

			DataInputStream entrada = new DataInputStream(new FileInputStream(archivo));

			byte[] buff = new byte[sf];

			entrada.read(buff);

			byte[] res = new byte[sf+sp];

			for(int i = 0; i < sp; i ++)
				res[i] = prev[i];
			for(int i = 0; i < sf; i ++)
				res[i+sp] = buff[i];

			return res;



		}

	}

	byte[] Delete(String loc, String dir){

		int pos = 0;
		String aux = "";


		while(pos < loc.length() && loc.charAt(pos) != '?'){
			aux += loc.charAt(pos);
			pos ++;
		}
		loc = aux;	
								
		if(loc.equals("")) loc = "index.html" ;

		System.out.println(	"Petición DELETE: " + loc + ", origen: " + dir );

		String ret;
		boolean borrar = true;
		File archivo = new File(loc);		

		if(!archivo.exists()){
			ret = NOENC + headers; archivo = new File("NotFound.html");	borrar = false;
		} else if(!archivo.canRead() || !dir.equals("8.12.0.50")){
			ret = NOLEC + headers; archivo = new File("Forbidden.html"); borrar = false;
		} else { ret = BUENO + headers; }

		if(borrar){
			archivo.delete();
			archivo = new File("borrado.html");
		}

		String linea;
		try{
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			while ((linea = br.readLine()) != null)				
				ret += linea + "\r\n";						
		} catch(Exception ex){
			System.out.println(ex.toString());
		}

		return ret.getBytes();	
	}

	byte[] Head(String loc, String dir){
		int pos = 0;
		String aux = "";

		while(pos < loc.length() && loc.charAt(pos) != '?'){
			aux += loc.charAt(pos);
			pos ++;
		}
		loc = aux;	
								
		if(loc.equals("")) loc = "index.html";

		System.out.println(	"Petición HEAD: " + loc + ", origen: "+ dir);

		String ret;	
		File archivo = new File(loc);	

		if(!archivo.exists()){
			ret = NOENC + headers;					
		} else if(!archivo.canRead()){
			ret = NOLEC + headers; 	
		} else { ret = BUENO + headers; }

		return ret.getBytes();

	}

	byte[] Otro(String dir){
		System.out.println("Petición no reconocida, origen: " + dir);
		return BUENO.getBytes();	
	}

};