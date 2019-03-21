import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from MainWindow import MainWindow

win = MainWindow()
win.connect("delete-event", Gtk.main_quit)
Gtk.main()