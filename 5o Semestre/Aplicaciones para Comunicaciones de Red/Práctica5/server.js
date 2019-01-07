let port = process.env.PORT;
if (port == null || port === '' || port === undefined) {
  port = 8000;
}
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var ioClient = require('socket.io-client');
let dataDictionary = new Map();

app.get('/', function (req, res) {
  res.sendFile(__dirname + '/public/index.html');
});

let serverList = new Map();
serverList.set('host1', 'http://localhost:3000');
serverList.set('host2', 'http://localhost:4000');

let intIP = new Map();
intIP.set('http://localhost:3000', 'host1');
intIP.set('http://localhost:4000', 'host2');

dataDictionary.set('Arbol', 'host1');
dataDictionary.set('Maquina', 'host1');
dataDictionary.set('Red', 'host1');
dataDictionary.set('Procolo', 'host1');
dataDictionary.set('Chispa', 'host1');
dataDictionary.set('Teléfono', 'host1');
dataDictionary.set('Mundo', 'host1');
dataDictionary.set('Locutor', 'host1');
dataDictionary.set('Compromiso', 'host1');
dataDictionary.set('Avión', 'host1');
dataDictionary.set('Lima', 'host1');
dataDictionary.set('Servilleta', 'host1');
dataDictionary.set('Ingenuo', 'host1');
dataDictionary.set('Nuevo', 'host1');
dataDictionary.set('Marcapasos', 'host1');
dataDictionary.set('Aeropuerto', 'host1');
dataDictionary.set('Físico', 'host1');
dataDictionary.set('Otoño', 'host1');
dataDictionary.set('Varilla', 'host1');
dataDictionary.set('Inundar', 'host1');
dataDictionary.set('Guardaespaldas', 'host1');
dataDictionary.set('Portón', 'host1');
dataDictionary.set('Dentista', 'host1');
dataDictionary.set('Morse', 'host1');
dataDictionary.set('Quiosco', 'host1');
dataDictionary.set('Tejido', 'host1');
dataDictionary.set('Entumecer', 'host1');
dataDictionary.set('Jarrón', 'host1');
dataDictionary.set('Observatorio', 'host1');
dataDictionary.set('Apellido', 'host1');
dataDictionary.set('Cucurucho', 'host1');
dataDictionary.set('Pisar', 'host1');
dataDictionary.set('Viaje', 'host1');
dataDictionary.set('Lejos', 'host1');
dataDictionary.set('Delfín', 'host1');
dataDictionary.set('Sordo', 'host1');
dataDictionary.set('Aceite', 'host1');
dataDictionary.set('Robar', 'host1');
dataDictionary.set('Balbucear', 'host1');
dataDictionary.set('Catástrofe', 'host1');


dataDictionary.set('Nube', 'host2');
dataDictionary.set('Arista', 'host2');
dataDictionary.set('Mano', 'host2');
dataDictionary.set('Computadora', 'host2');
dataDictionary.set('Pastel', 'host2');
dataDictionary.set('Pintura', 'host2');
dataDictionary.set('Abismo', 'host2');
dataDictionary.set('Ascensor', 'host2');
dataDictionary.set('Espátula', 'host2');
dataDictionary.set('Pulga', 'host2');
dataDictionary.set('Pinza', 'host2');
dataDictionary.set('Rapiña', 'host2');
dataDictionary.set('Vapor', 'host2');
dataDictionary.set('Calavera', 'host2');
dataDictionary.set('Altiplano', 'host2');
dataDictionary.set('Colisión', 'host2');
dataDictionary.set('Peste', 'host2');
dataDictionary.set('Mago', 'host2');
dataDictionary.set('Militar', 'host2');
dataDictionary.set('Sonámbulo', 'host2');
dataDictionary.set('Vanidoso', 'host2');
dataDictionary.set('Merienda', 'host2');
dataDictionary.set('Piano', 'host2');
dataDictionary.set('Gasa', 'host2');
dataDictionary.set('Bufanda', 'host2');
dataDictionary.set('Grano', 'host2');
dataDictionary.set('Barriga', 'host2');
dataDictionary.set('Tazón', 'host2');
dataDictionary.set('Aceituna', 'host2');
dataDictionary.set('Templo', 'host2');
dataDictionary.set('Pared', 'host2');
dataDictionary.set('Exterior', 'host2');
dataDictionary.set('Sombrero', 'host2');
dataDictionary.set('Viñedo', 'host2');
dataDictionary.set('Colilla', 'host2');
dataDictionary.set('Etiqueta', 'host2');
dataDictionary.set('Ratonera', 'host2');
dataDictionary.set('Embudo', 'host2');
dataDictionary.set('Comisaría', 'host2');
dataDictionary.set('Hielo', 'host2');

io.on('connection', socket => {
  console.log('holaa: ' + port);
  socket.on('request-ip', (word) => {
    const ip = dataDictionary.get(word);
    console.log(word, serverList.get(ip));
    socket.emit('ip', serverList.get(ip));
  });

  socket.on('add', (data) => {
    console.log(data);
    dataDictionary.set(data.word, intIP.get(data.ip));
    socket.emit('added');
  });

  socket.on('erase', (word) => {
    console.log('erase', word, dataDictionary.get(word));
    const ip = dataDictionary.get(word);
    if (ip && ip !== 'false') {
      dataDictionary.delete(word);
      let clientSock = ioClient(ip);
      clientSock.emit('erase', word);
      clientSock.on('erased', () => {
        clientSock.disconnect();
      });
      socket.emit('erased');
    }
  });

  socket.on('disconnect', function () {
    console.log('user disconnected');
  });
});

http.listen(port, function () {
  console.log('listening on *:' + port);
});