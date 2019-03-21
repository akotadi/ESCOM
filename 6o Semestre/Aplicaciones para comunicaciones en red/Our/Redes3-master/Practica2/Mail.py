import smtplib
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

COMMASPACE = ', '

# Define params
rrdpath = '../RRD/'
pngpath = '../IMG/'
fname = 'trend.rrd'

class Mail():
	mailsender = "galardones.ipn@gmail.com"
	password = "drejnfaeduqydwrp"
	#mailreceip = ["manzanoivan95@gmail.com"]

	def send(self, to, subject, imagen ):
		print "Email enviado: ", subject
		msg = MIMEMultipart()
		msg['Subject'] = subject
		msg['From'] = self.mailsender
		msg['To'] = COMMASPACE.join(to)
		fp = open(imagen, 'rb')
		img = MIMEImage(fp.read())
		fp.close()
		msg.attach(img)
		server = smtplib.SMTP_SSL('smtp.gmail.com', 465)
		server.ehlo()
		server.login(self.mailsender, self.password)
		server.sendmail(self.mailsender, to, msg.as_string())
		server.close()


