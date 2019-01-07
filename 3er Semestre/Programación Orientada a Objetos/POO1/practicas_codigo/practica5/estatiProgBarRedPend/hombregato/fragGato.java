


	Socket cliente;  
	PrintWriter writer;
        BufferedReader reader;
  	Thread hilo;
  	int puerto;

	


	int i=0;
   	while(i==0)
   	{
    			i=1;
    			System.out.println("Esperando por el servidor . . .");
    			try {
				cliente=new Socket( "localhost", puerto);
    			} catch ( IOException e) {
            			System.out.println("ERROR(1)");
            			i=0;
   			}
   	}
   	System.out.println("Connectado al servidor.");
   	try {    
            		writer = new PrintWriter(cliente.getOutputStream());  
			InputStreamReader isReader = new InputStreamReader(cliente.getInputStream());
             reader = new BufferedReader(isReader);
   	} catch ( IOException e) {
       			System.out.println("ERROR(2)");
   	}
   	hilo = new Thread (this);
   	hilo.start ();

public void run()
{
	String message;
	int j,k=0;

   	for(;;)
   	{
    		j=0;
    		try {
			message = reader.readLine();
			k=Integer.parseInt(message);
    		} catch ( IOException e) {
         		j=1;
    		}
    		if (j==0) {
				
				
		}
	}
}

/*
	writer.println(""+i);
        writer.flush();
*/

public void actionPerformed(ActionEvent e) {  
     Button b=(Button)e.getSource();
     if(b==bfeliz){
      changeTexture(texture, "carafeliz.jpg", sphere);
     }
     if(b==benfer){
       changeTexture(texture, "caraenfermo.jpg", sphere);
     }
}



