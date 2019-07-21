var scanner;
var activeCameraId;
var aCameras;

function openQRCamera(node) {
	console.log(node);
	var reader = new FileReader();
	reader.onload = function() {
		node.value = "";
		qrcode.callback = function(res) {
			if(res instanceof Error) {
				alert("No QR code found. Please make sure the QR code is within the camera's frame and try again.");
			} else {
				alert("Read: " + res);
				var input = document.getElementById('qrcode-text');
				input.value = res;
				// node.parentNode.parentNode.innerHTML = res;
			}
		};
		console.log(reader.result);
		qrcode.decode(reader.result);
	};
	reader.readAsDataURL(node.files[0]);
}

function showQRIntro() {
	return confirm("Use your camera to take a picture of a QR code.");
}

function startQRReader(){
	self.scanner = new Instascan.Scanner({ video: document.getElementById('preview'), scanPeriod: 5 });
	self.scanner.addListener('scan', function (content) {
		var input = document.getElementById('qrcode-text');
		input.value = content;
		document.getElementById("btnCloseModal").click();
	});
	Instascan.Camera.getCameras().then(function (cameras) {
		self.aCameras = cameras.slice(0,cameras.length);
		if (cameras.length > 0) {
			self.activeCameraId = cameras[0].id;
			self.scanner.start(cameras[0]);
			const list = document.getElementById('listCameras');
			let aux = 0;
			cameras.forEach(function(cam){
				let node = document.createElement("a");
				node.id = cam.id;
				node.classList.add("camNode");
				node.classList.add("list-group-item");
				node.classList.add("list-group-item-action");
				node.classList.add("col-sm-2");
				node.addEventListener("click", changeCam);
				if (aux === 0) {
					node.classList.add("active");
				}
				node.setAttribute("value", aux++);
				var textnode = document.createTextNode(cam.name);
				node.appendChild(textnode);
				list.appendChild(node);
			});
		} else {
			console.error('No cameras found.');
		}
	}).catch(function (e) {
		console.error(e);
	});
}

function changeCam(){
	const _this = this;
	const aCamNodes = document.querySelectorAll('.camNode');
	aCamNodes.forEach(function(cam){
		cam.classList.remove("active");
	});
	_this.classList.add("active");
	aCameras.forEach(function(cam){
		if (cam.id === _this.id) {
			self.activeCameraId = _this.id;
			self.scanner.start(cam);
		}
	});
}

function closeQRReader() {
	self.scanner.stop();
	const listCameras = document.getElementById('listCameras');
	for (var i = listCameras.children.length - 1; i >= 0; i--) {
		listCameras.removeChild(listCameras.children[i]);
	}
}

$('#btnDataPDF').click(function(){
	var doc = new jsPDF("landscape");

	doc.setFontSize(40);
	doc.text(35, 25, "Octonyan loves jsPDF");
	doc.lines([[2,2],[-2,2],[1,1,2,2,3,3],[2,1]], 212,110, 10);

	// Set the document to automatically print via JS
	// doc.autoPrint();
	doc.save('Diploma.pdf');
});