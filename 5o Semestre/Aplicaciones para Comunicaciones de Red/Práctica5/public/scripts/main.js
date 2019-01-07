// const ipPrincipal = 'localhost';
// const server = ['http://localhost:3000', 'http://localhost:4000'];
// let socketForDefinition;
// // let principalSocket = io.connect(server[Math.floor((Math.random() * 1) + 0)], {'forceNew': true});
// let principalSocket = io();

// principalSocket.on('def', def => {
// 	$('#output').val(def);
// 	$('#output').trigger('click');
// });

// $(document).ready(function() {
// 	$( '#btnSearch' ).click(function() {
// 		let word = document.getElementById('input').value;

// 		principalSocket.emit('request-def', word);
// 	});

// 	$( '#btnAdd' ).click(function() {
// 		let word = document.getElementById('input').value;
// 		let def = document.getElementById('output').value;
// 		principalSocket.emit('add', { word: word, def: def });
// 	});

// 	$( '#btnDelete' ).click(function() {
// 		principalSocket.emit('erase', document.getElementById('input').value);
// 		document.getElementById('input').value = '';
// 		document.getElementById('output').value = '';
// 	});
// });