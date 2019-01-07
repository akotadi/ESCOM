import select, socket, sys, Queue, time
from random import seed
from random import randint
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setblocking(0)
server.bind(('localhost', 12345))
server.listen(5)
inputs = [server]
outputs = []
message_queues = {}
times = {}
words = {}
games = {}
errors = {}
line = {}

MAX_ERRORS = 7

seed(time.clock())

while inputs:
    readable, writable, exceptional = select.select(inputs, outputs, inputs)
    for s in readable:
        if s is server:
            connection, client_address = s.accept()
            print 'New Client'
            connection.setblocking(0)
            inputs.append(connection)
            message_queues[connection] = Queue.Queue()
            file  = open("palabras", "r")
            for x in range(0,randint(0, 20)*5):
                word = file.readline()
            word = file.readline()
            print len(word)-1
            print word
            words[connection] = word
            games[connection] = len(word)-1
            errors[connection] = MAX_ERRORS
            connection.send(str(len(word)-1)+"\n")
            sSend = ""
            for x in range(0, len(word)-1):
                sSend = sSend + "_ "
            line[connection] = list(sSend)
            connection.send(sSend+"\n")
            file.close()
            times[connection] = time.time();
        else:
            data = s.recv(2096)
            if data:
                print 'Received', repr(data[:1])

                data = data[:1]

                flag = 0
                aux = 0

                sSend = line[s]

                for i, c in enumerate(words[s]):
                    if (data == c) and (sSend[i*2] != c):
                        sSend[i*2] = c
                        games[s] = games[s] - 1
                        aux = aux + 1
                        flag = 1

                if games[s] == 0:
                    message_queues[s].put('0')
                elif flag == 1:
                    message_queues[s].put('1')
                    message_queues[s].put(str(aux))
                else:
                    message_queues[s].put('2')
                    errors[s] = errors[s] - 1

                line[s] = sSend

                message_queues[s].put("".join(sSend))

                if (errors[s] == 0) or (games[s] == 0):
                    message_queues[s].put("Juego finalizado en "+str(time.time()-times[s])+"s.")

                if s not in outputs:
                    outputs.append(s)
            else:
                if s in outputs:
                    outputs.remove(s)
                inputs.remove(s)
                s.close()
                del message_queues[s]

    for s in writable:
        try:
            next_msg = message_queues[s].get_nowait()
        except Queue.Empty:
            outputs.remove(s)
        else:
            s.send(next_msg+"\n")

    for s in exceptional:
        inputs.remove(s)
        if s in outputs:
            outputs.remove(s)
        s.close()
        del message_queues[s]