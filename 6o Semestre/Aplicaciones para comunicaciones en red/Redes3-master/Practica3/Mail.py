import smtplib
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
COMMASPACE = ', '

class Mail():
	mailsender = "testusernamem1@gmail.com"
	password = "Manzano1416"
	#mailreceip = ["manzanoivan95@gmail.com"]

	def send(self, to, subject, texto ):
		print "Email enviado: ", subject
		#msg = MIMEMultipart()
		#msg['Subject'] = subject
		#msg['From'] = self.mailsender
		#msg['To'] = COMMASPACE.join(to)
		#fp = open(imagen, 'rb')
		#img = MIMEImage(fp.read())
		#fp.close()
		#msg.attach(img)
		server = smtplib.SMTP_SSL('smtp.gmail.com', 465)
		server.ehlo()
		server.login(self.mailsender, self.password)
		server.sendmail(self.mailsender, to, texto)
		server.close()
