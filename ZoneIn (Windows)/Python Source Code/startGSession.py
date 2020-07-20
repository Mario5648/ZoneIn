import tkinter
from tkinter import messagebox
from socket import AF_INET, socket, SOCK_STREAM
from threading import Thread
import time
import os
import subprocess

"""""""""""""""""""""""""""""""""""""""""""""
                host()

Description:
    - Host is responsible in connecting all users together and update all
    users with the most current information such as time or messages.


"""""""""""""""""""""""""""""""""""""""""""""

def host():


    """""
    this will count down in seconds and will broadcast the
    current time to all users
    """""
    def timer(seconds):
        while True:

            uin = seconds
            try:
                whenStop = abs(int(uin))
            except KeyboardInterrupt:
                break
            except:
                print("Not a number!")

            while whenStop > 0:
                m,s = divmod(whenStop, 60)
                h,m = divmod(m,60)
                time_left = str(h).zfill(2) + ":"+str(m).zfill(2)+":"+str(s).zfill(2)
                #print(time_left)
                #-- If the server is down then end this process --#
                if broadcastTime(str(time_left)) == 0:
                    endAll()
                    SERVER.close()
                    break
                time.sleep(1)
                whenStop -=1
            endAll()
            SERVER.close()
            break




    """""""""""""""""""""""""""""""""
        accept_incoming_connections()

    Description:
        - This is in charge of accepting users
        this will notify the users to type their password

    """""""""""""""""""""""""""""""""""
    def accept_incoming_connections():
        while True:
            client, client_address = SERVER.accept()
            print("%s:%s has connected." % client_address)
            client.send(bytes("Type Host's password! and hit enter!", "utf8"))
            addresses[client] = client_address
            Thread(target=handle_client, args=(client,)).start()



    """""""""""""""""""""""""""""""""
        handle_client(client)

    Description:
        - This will check if the user has sent the right password, if
        not then it will close their connection. if the user maintains the
        connection then it will display a welcome screen. This will also check
        who sends a message and broadcasts it to all users. it is also
        responsible to end connections to people who quit. If the host
        quits then the whole server will shutdown

    """""""""""""""""""""""""""""""""""

    def handle_client(client):
        password = client.recv(BUFSIZ).decode("utf8")
        file = open('C:/ZoneIn/WritableFiles/pd.txt','r')
        passwordHost = file.readline()
        if password != str(passwordHost).strip("\n"):
            client.send(bytes("{quit}", "utf8"))
            client.close()
            del clients[client]
            file.close()
            return
        file.close()
        welcome = 'Welcome %s!' % name
        client.send(bytes(welcome, "utf8"))
        msg = "%s has joined the chat!" % name
        broadcast(bytes(msg, "utf8"))
        clients[client] = name

        while True:
            msg = client.recv(BUFSIZ)
            if msg != bytes("{quit}", "utf8"):
                broadcast(msg, name+": ")
            else:
                if name == HOSTNAME:
                    ENDFLAG[0] =1
                    endAll()
                    SERVER.close()
                    return
                client.send(bytes("{quit}", "utf8"))
                client.close()
                del clients[client]
                broadcast(bytes("%s has left the chat." % name, "utf8"))
                break

    """""""""""""""
        This will go through every thread and update their messages
    """""""""""
    def broadcast(msg, prefix=""):
        for sock in clients:
            sock.send(bytes(prefix, "utf8")+msg)

    """""""""""""""
        This one is responsible for updating the current time to all users
    """""""""""""""
    def broadcastTime(seconds):
        if ENDFLAG[0] == 1 :
            return 0
        for sock in clients:
            sock.send(bytes("_TIME_ "+str(seconds), "utf8"))
        return 1

    """""""""""""""""""""
        End all connections
    """""""""""""""""""""
    def endAll():
        c = clients.copy()
        for sock in c:
            sock.send(bytes("{quit}", "utf8"))
            sock.close()
            del clients[sock]
        SERVER.close()



    #-- store each client thread --#
    clients = {}
    #-- Store each clients adress --#
    addresses = {}


    #-- get the server password and the amount of time in seconds --#
    file = open('C:/ZoneIn/WritableFiles/pd.txt', 'r')
    info = file.readlines()
    passwordHOST = info[0].strip("\n")
    timerSeconds = info[1].strip("\n")
    file.close()


    #-- Flag is reponsible to tell timer when to stop --#
    ENDFLAG = [0]
    file = open("C:/ZoneIn/WritableFiles/name.txt","r")
    name = str(file.readline())
    file.close()
    #-- this will get the name of the user from the name file --#
    HOSTNAME = name
    HOST = ''
    PORT = 33000
    BUFSIZ = 1024
    ADDR = (HOST, PORT)


    #-- Create server --#
    SERVER = socket(AF_INET, SOCK_STREAM)
    SERVER.bind(ADDR)

    if __name__ == "__main__":
        SERVER.listen(5)
        print("Waiting for connection...")

        ACCEPT_THREAD = Thread(target=accept_incoming_connections)
        t1 =Thread(target=timer,args=[timerSeconds]).start()
        ACCEPT_THREAD.start()
        ACCEPT_THREAD.join()
        SERVER.close()

""""""""""""""""""""""
    This will find the total time in
    seconds. then it will start the host server
"""""""""""""""""""""""
def startH(totalSeconds,password):
    file = open('C:/ZoneIn/WritableFiles/pd.txt','w')
    file.write(str(password)+"\n")
    file.write(str(totalSeconds)+"\n")
    file.close()
    host()

file = open("C:/ZoneIn/WritableFiles/GroupSessionSeconds.txt","r")
totalSeconds = file.readline()
file.close()

file = open("C:/ZoneIn/WritableFiles/GroupSessionPassword.txt","r")
password = file.readline()
file.close()
startH(totalSeconds,password)
