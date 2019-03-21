import gi
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk, GdkPixbuf
import os
import threading
import SNMP
from AnalysisWindow import AnalysisWindow

class AgentInfo:
    def __init__(self, name, ip, port, community, infoBox, logoBox):
        self.name = name
        self.ip = ip
        self. port = port
        self.community = community
        self.infoBox = infoBox
        self.logoBox = logoBox


class ObjectId(object):
    def __init__(self, description, oid):
        self.description = description
        self.oid = oid


class AgentDescription(object):
    def __init__(self, label, description):
        self.label = label
        self.description = description

def getAgentsInfo(infoForIp):
    
    for key in infoForIp:
        grid = Gtk.Grid(column_spacing=10)
        agentDescriptions = []
        oids = [
            # ObjectId('Location', '1.3.6.1.2.1.1.6.0'),
            # ObjectId('Up Time', '1.3.6.1.2.1.1.3.0'),
            # ObjectId('Contact', '1.3.6.1.2.1.1.4.0'),
            # ObjectId('Name', '1.3.6.1.2.1.1.5.0'),
            ObjectId('OS', '1.3.6.1.2.1.1.1.0'),
            ObjectId('Network Interfaces', '1.3.6.1.2.1.2.1.0')
        ]

        for oid in oids:
            result = SNMP.consultaSNMP(
                infoForIp[key].community,
                infoForIp[key].ip,
                infoForIp[key].port,
                oid.oid
                )
            agentDescriptions.append(AgentDescription(oid.description, result))

        networkInterfaces = SNMP.consultaSNMP(
                    infoForIp[key].community,
                    infoForIp[key].ip,
                    infoForIp[key].port,
                    '1.3.6.1.2.1.2.1.0'
                    )
        for i in range(int(networkInterfaces, 10)):
            interfaceName = str(SNMP.consultaSNMP(
                    infoForIp[key].community,
                    infoForIp[key].ip,
                    infoForIp[key].port,
                    '1.3.6.1.2.1.2.2.1.2.' + str(i + 1)
                    ))

            interfaceStatus = SNMP.consultaSNMP(
                    infoForIp[key].community,
                    infoForIp[key].ip,
                    infoForIp[key].port,
                    '1.3.6.1.2.1.2.2.1.8.' + str(i + 1)
                    )
            if interfaceStatus == '1':
                interfaceStatus = 'up'
            elif interfaceStatus == '2':
                interfaceStatus = 'down'
            agentDescriptions.append(AgentDescription(interfaceName, interfaceStatus))
        # agentDescriptions.append(AgentDescription('Version SNMP', 'v2c'))
        lineNumber = 0
        for agentDescription in agentDescriptions:
            label1 = Gtk.Label(agentDescription.label)
            label1.set_alignment(0, 0.5)
            hbox1 = Gtk.HBox()
            hbox1.pack_start(label1, True, True, 0)
            grid.attach(hbox1, 0, lineNumber, 1, 1)
            label2 = Gtk.Label(agentDescription.description)
            label2.set_alignment(0, 0.5)
            hbox2 = Gtk.HBox()
            hbox2.pack_start(label2, True, True, 0)
            grid.attach(hbox2, 1, lineNumber, 1, 1)
            lineNumber += 1
        infoForIp[key].infoBox.pack_start(grid, True, True, 0)

        img = Gtk.Image()
        osystem = str(SNMP.consultaSNMP(
                infoForIp[key].community,
                infoForIp[key].ip,
                infoForIp[key].port,
                '1.3.6.1.2.1.1.1.0'
                ))
        if 'Windows' in osystem:
            filename = 'windows.png'
        else:
            filename = 'linux.png'
        pixbuf = GdkPixbuf.Pixbuf.new_from_file_at_scale(
            filename=filename, 
            width=100, 
            height=100, 
            preserve_aspect_ratio=True
        )
        img = Gtk.Image.new_from_pixbuf(pixbuf)
        infoForIp[key].logoBox.pack_start(img, True, True, 0)


class AddAgentWindow(Gtk.Window):


    def createAgentFile(self, btn):
        string = self.entry1.get_text() + '\n'
        string += self.entry2.get_text() + '\n'
        string += self.entry3.get_text() + '\n'
        string += self.entry4.get_text() + '\n'
        string += self.entry5.get_text()
        fout = open('agents/' + self.entry2.get_text(), 'w')
        fout.write(string)
        fout.close()
        self.addAgentWindow.init()
        self.destroy()

    def __init__(self, addAgentWindow):
        self.addAgentWindow = addAgentWindow
        Gtk.Window.__init__(self, title="Add Agent")
        grid = Gtk.Grid(column_spacing=20)
        self.add(grid)
        label1 = Gtk.Label('Name')
        hbox1 = Gtk.HBox()
        hbox1.pack_start(label1, True, True, 0)
        grid.attach(hbox1, 0, 0, 1, 1)
        label2 = Gtk.Label('IP')
        hbox2 = Gtk.HBox()
        hbox2.pack_start(label2, True, True, 0)
        grid.attach(hbox2, 0, 1, 1, 1)
        label3 = Gtk.Label('Port')
        hbox3 = Gtk.HBox()
        hbox3.pack_start(label3, True, True, 0)
        grid.attach(hbox3, 0, 2, 1, 1)
        label4 = Gtk.Label('Community')
        hbox4 = Gtk.HBox()
        hbox4.pack_start(label4, True, True, 0)
        grid.attach(hbox4, 0, 3, 1, 1)
        label5 = Gtk.Label('SNMP version')
        hbox5 = Gtk.HBox()
        hbox5.pack_start(label5, True, True, 0)
        grid.attach(hbox5, 0, 4, 1, 1)

        self.entry1 = Gtk.Entry()
        hbox1 = Gtk.HBox()
        hbox1.pack_start(self.entry1, True, True, 0)
        grid.attach(hbox1, 1, 0, 1, 1)
        self.entry2 = Gtk.Entry()
        hbox2 = Gtk.HBox()
        hbox2.pack_start(self.entry2, True, True, 0)
        grid.attach(hbox2, 1, 1, 1, 1)
        self.entry3 = Gtk.Entry()
        hbox3 = Gtk.HBox()
        hbox3.pack_start(self.entry3, True, True, 0)
        grid.attach(hbox3, 1, 2, 1, 1)
        self.entry4 = Gtk.Entry()
        hbox4 = Gtk.HBox()
        hbox4.pack_start(self.entry4, True, True, 0)
        grid.attach(hbox4, 1, 3, 1, 1)
        self.entry5 = Gtk.Entry()
        hbox5 = Gtk.HBox()
        hbox5.pack_start(self.entry5, True, True, 0)
        grid.attach(hbox5, 1, 4, 1, 1)

        addBtn = Gtk.Button('Add')
        addBtn.connect("clicked", self.createAgentFile)
        grid.attach(addBtn, 0, 5, 2, 1)
        self.show_all()


def openAddAgentWindow(btn, gridWindow):
    win = AddAgentWindow(gridWindow)

def openAnalysis(wid, ev, ip, community, port):
    AnalysisWindow(ip, community, port, ['serchgabriel97@gmail.com'], True)


def removeAgent(btn, gridWindow, ip):
 	os.remove('agents/' + ip)
 	gridWindow.init()


class GridWindow(Gtk.Window):

    def init(self, ev=None):
        for i in self.get_children():
            self.remove(i)

        self.set_default_size(1024, 420)

        scroll = Gtk.ScrolledWindow(hexpand=True, vexpand=True)
        self.add(scroll)
        infoForIp = dict()

        mainVbox = Gtk.VBox()

        menuGrid = Gtk.Grid(column_spacing=10)
        addbtn = Gtk.Button('+')
        addbtn.connect('clicked', openAddAgentWindow, self)
        menuGrid.attach(addbtn, 0, 0, 1, 1)
        addbtn = Gtk.Button('Refresh')
        addbtn.connect('clicked', self.init)
        menuGrid.attach(addbtn, 1, 0, 1, 1)

        grid = Gtk.Grid(column_spacing=20)
        mainVbox.pack_start(menuGrid, True, True, 0)
        mainVbox.pack_start(grid, True, True, 0)

        scroll.add(mainVbox)

        label1 = Gtk.Label('Name')
        hbox1 = Gtk.HBox()
        hbox1.pack_start(label1, True, True, 0)
        grid.attach(hbox1, 0, 0, 1, 1)
        label2 = Gtk.Label('IP')
        hbox2 = Gtk.HBox()
        hbox2.pack_start(label2, True, True, 0)
        grid.attach(hbox2, 1, 0, 1, 1)
        label3 = Gtk.Label('Port')
        hbox3 = Gtk.HBox()
        hbox3.pack_start(label3, True, True, 0)
        grid.attach(hbox3, 2, 0, 1, 1)
        label4 = Gtk.Label('Community')
        hbox4 = Gtk.HBox()
        hbox4.pack_start(label4, True, True, 0)
        grid.attach(hbox4, 3, 0, 1, 1)
        label5 = Gtk.Label('Information')
        hbox5 = Gtk.HBox()
        hbox5.pack_start(label5, True, True, 0)
        grid.attach(hbox5, 4, 0, 1, 1)
        label6 = Gtk.Label('Logo')
        hbox6 = Gtk.HBox()
        hbox6.pack_start(label6, True, True, 0)
        grid.attach(hbox6, 5, 0, 1, 1)
        label7 = Gtk.Label('Delete')
        hbox7 = Gtk.HBox()
        hbox7.pack_start(label7, True, True, 0)
        grid.attach(hbox7, 6, 0, 1, 1)

        fileNumber = 1
        for file in os.listdir('agents'):
            with open('agents/' + file) as f:
                content = f.readlines()
            content = content[:4]
            for i in range(len(content)):
                content[i] = content[i].replace('\n', '')
            lineNumber = 0
            for line in content:
                label = Gtk.Label(line)
                evbox = Gtk.EventBox()
                evbox.connect('button-press-event', openAnalysis, content[1], content[3], content[2])
                vbox = Gtk.VBox()
                evbox.add(vbox)
                vbox.pack_start(label, True, True, 0)
                grid.attach(evbox, lineNumber, fileNumber, 1, 1)
                lineNumber += 1
            vbox = Gtk.VBox()
            grid.attach(vbox, lineNumber, fileNumber, 1, 1)
            logoBox = Gtk.VBox()
            grid.attach(logoBox, lineNumber + 1, fileNumber, 1, 1)
            infoForIp[content[1]] = AgentInfo(content[0], content[1], content[2], content[3], vbox, logoBox)
            
            deleteBtn = Gtk.Button('Eliminar')
            deleteBtn.connect('clicked', removeAgent, self, content[1])
            grid.attach(deleteBtn, lineNumber + 2, fileNumber, 1, 1)
            fileNumber += 1

        thread = threading.Thread(target=getAgentsInfo, args=(infoForIp,))
        thread.start()
        thread.join()
        self.connect("destroy", Gtk.main_quit)
        self.show_all()

    def __init__(self):
        Gtk.Window.__init__(self, title="Observium 2.0")
        self.init()

win = GridWindow()
Gtk.main()