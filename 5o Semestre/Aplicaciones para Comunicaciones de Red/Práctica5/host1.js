let port = process.env.PORT;
if (port == null || port === '' || port === undefined) {
  port = 3000;
}
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var ioClient = require('socket.io-client');

app.get('/', function (req, res) {
  res.sendFile(__dirname + '/public/index.html');
});

let dataDictionary = new Map();

dataDictionary.set('Arbol', 'Planta perenne, de tronco leñoso y elevado, que se ramifica a cierta altura del suelo.');
dataDictionary.set('Maquina', 'Artificio para aprovechar, dirigir o regular la acción de una fuerza.');
dataDictionary.set('Red', 'Aparejo hecho con hilos, cuerdas o alambres trabados en forma de mallas, y convenientemente dispuesto para pescar, cazar, cercar, sujetar, etc.');
dataDictionary.set('Procolo', 'Secuencia detallada de un proceso de actuación científica, técnica, médica, etc.');
dataDictionary.set('Chispa', 'Partícula encendida que salta del fuego o del choque entre piedras, metales, etc.');
dataDictionary.set('Teléfono', 'Conjunto de aparatos e hilos conductores con los cuales se transmite a distancia la palabra y toda clase de sonidos por la acción de la electricidad.');
dataDictionary.set('Mundo', 'Conjunto de todo lo existente.');
dataDictionary.set('Locutor', 'Persona que tiene por oficio hablar por radio o televisión para dar noticias, presentar programas, etc.');
dataDictionary.set('Compromiso', 'Obligación contraída.');
dataDictionary.set('Avión', 'Aeronave más pesada que el aire, provista de alas, cuya sustentación y avance son consecuencia de la acción de uno o varios motores.');
dataDictionary.set('Lima', 'Fruto del limero, de forma esferoidal aplanada y de unos cinco centímetros de diámetro, pezón bien saliente de la base, corteza lisa y amarilla, y pulpa verdosa, dividida en gajos, comestible, jugosa y de sabor algo dulce.');
dataDictionary.set('Servilleta', 'Pieza de tela o papel que usa cada comensal para limpiarse los labios y las manos.');
dataDictionary.set('Ingenuo', 'Candoroso, sin doblez.');
dataDictionary.set('Nuevo', 'Recién hecho o fabricado.');
dataDictionary.set('Marcapasos', 'Aparato electrónico de tamaño pequeño que estimula y regula el ritmo del corazón.');
dataDictionary.set('Aeropuerto', 'Área destinada al aterrizaje y despegue de aviones dotada de instalaciones para el control del tráfico aéreo y de servicios a los pasajeros.');
dataDictionary.set('Físico', 'Ciencia que estudia las propiedades de la materia y de la energía, y las relaciones entre ambas.');
dataDictionary.set('Otoño', 'Estación del año que, astronómicamente, comienza en el equinoccio del mismo nombre y termina en el solsticio de invierno.');
dataDictionary.set('Varilla', 'Barra larga y delgada.');
dataDictionary.set('Inundar', 'Dicho del agua: Cubrir un lugar determinado o un territorio.');
dataDictionary.set('Guardaespaldas', 'Persona que acompaña asiduamente a otra con la misión de protegerla.');
dataDictionary.set('Portón', 'Puerta que separa el zaguán del resto de la casa.');
dataDictionary.set('Dentista', 'Persona profesionalmente dedicada a cuidar la dentadura, reponer artificialmente sus faltas y curar sus enfermedades.');
dataDictionary.set('Morse', 'Sistema de telegrafía que utiliza un código consistente en la combinación de rayas y puntos.');
dataDictionary.set('Quiosco', 'Templete o pabellón en parques o jardines, generalmente abierto por todos sus lados, que entre otros usos ha servido tradicionalmente para celebrar conciertos populares.');
dataDictionary.set('Tejido', 'Textura de una tela.');
dataDictionary.set('Entumecer', 'Impedir o entorpecer el movimiento o acción de un miembro o de un nervio.');
dataDictionary.set('Jarrón', 'Pieza arquitectónica en forma de jarro, con que se decoran edificios, galerías, escaleras, jardines, etc., puesta casi siempre sobre un pedestal y como adorno de remate.');
dataDictionary.set('Observatorio', 'Lugar o posición que sirve para hacer observaciones.');
dataDictionary.set('Apellido', 'Nombre de familia con que se distinguen las personas.');
dataDictionary.set('Cucurucho', 'Papel, cartón, barquillo, etc., arrollado en forma cónica, empleado para contener dulces, confites, helados, cosas menudas, etc.');
dataDictionary.set('Pisar', 'Poner el pie sobre algo.');
dataDictionary.set('Viaje', 'Traslado que se hace de una parte a otra por aire, mar o tierra.');
dataDictionary.set('Lejos', 'A gran distancia, en lugar o tiempo distante o remoto.');
dataDictionary.set('Delfín', 'Cetáceo piscívoro, de dos y medio a tres metros de largo, negro por encima, blanquecino por debajo, de cabeza voluminosa, ojos pequeños y pestañosos, boca muy grande, dientes cónicos en ambas mandíbulas, hocico delgado y agudo, y una sola abertura nasal. Vive en los mares templados y tropicales.');
dataDictionary.set('Sordo', 'Que padece una pérdida auditiva en mayor o menor grado.');
dataDictionary.set('Aceite', 'Líquido graso que se obtiene de frutos o semillas, como cacahuetes, algodón, soja, nueces, almendras, linaza, ricino o coco, y de algunos animales, como la ballena, la foca o el bacalao.');
dataDictionary.set('Robar', 'Quitar o tomar para sí con violencia o con fuerza lo ajeno.');
dataDictionary.set('Balbucear', 'Hablar o leer con pronunciación dificultosa, tarda y vacilante, trastocando a veces las letras o las sílabas.');
dataDictionary.set('Catástrofe', 'Suceso que produce gran destrucción o daño.');

const mainServer = 'http://localhost:8000';
const thisServer = 'http://localhost:' + port;

io.on('connection', socket => {
  console.log('new Client Connected');

  socket.on('request-def', (word) => {
    let definition = dataDictionary.get(word);
    console.log(word, definition);
    if (definition) {
      socket.emit('def', dataDictionary.get(word));
      return;
    }

    let clientSock = ioClient(mainServer);
    clientSock.emit('request-ip', word);
    clientSock.on('ip', ip => {
      console.log('ip', ip);
      clientSock.disconnect();
      if (ip && ip !== 'false') {
        clientSock = ioClient(ip);
        clientSock.emit('request-def', word);
        clientSock.on('def', def => {
          clientSock.disconnect();
          socket.emit('def', def);
        });
      } else {
        socket.emit('def', 'Not Found.');
      }
    });
  });

  socket.on('add', (data) => {
    console.log(data);
    dataDictionary.set(data.word, data.def);

    clientSock = ioClient(mainServer);
    clientSock.emit('add', { word: data.word, ip: thisServer });
    clientSock.on('added', () => {
      clientSock.disconnect();
    });
  });

  socket.on('erase', (word) => {
    let definition = dataDictionary.get(word);
    if (definition) {
      dataDictionary.delete(word);
    }

    clientSock = ioClient(mainServer);
    clientSock.emit('erase', word);
    clientSock.on('erased', () => {
      clientSock.disconnect();
    });
  });

  socket.on('disconnect', function () {
    console.log('user disconnected');
  });

});

http.listen(port, function () {
  console.log('listening on *:' + port);
});