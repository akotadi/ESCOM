import smtplib
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart

COMMASPACE = ', '

# Define params
rrdpath = '../RRD/'
pngpath = '../IMG/'
fname = 'trend.rrd'

mailsender = "galardones.ipn@gmail.com"
mailreceip = "jmch.7795@gmail.com"
# mailreceip = "tanibet.escom@gmail.com"
mailserver = 'smtp.gmail.com: 587'
password = 'drejnfaeduqydwrp'

def send_alert_attached(subject = "Alerta de sobrecarga", archivo = "deteccion"):
    """ Will send e-mail, attaching png
    files in the flist.
    """
    msg = MIMEMultipart()
    msg['Subject'] = subject
    msg['From'] = mailsender
    msg['To'] = mailreceip
    fp = open(pngpath+archivo+'.png', 'rb')
    img = MIMEImage(fp.read())
    fp.close()
    msg.attach(img)
    mserver = smtplib.SMTP(mailserver)
    mserver.starttls()
    # Login Credentials for sending the mail
    mserver.login(mailsender, password)

    mserver.sendmail(mailsender, mailreceip, msg.as_string())
    mserver.quit()
