from socket import AF_INET, socket, SOCK_STREAM
from threading import Thread
import tkinter
from flask import Flask, redirect,url_for,request,render_template
import noFunAIPY
import ytBlock
import _pickle as cPickle
import requests


#--This is to notify wheter or not to send a shutdown request to the PythonServer--#
FLASKSERVERFLAG = [0]

"""""""""""""""""""""""

variables:
    -app -create the Flask object

Functions:
    -home()   -Creates the home page
    -decide() -Call NoFunAIPY or ytBlock to make the decision
                returns either the acceptCode or the blockCode
    -shutdown() and shutdown_server() - This will allow the server
                                        to take in a shutdown request
"""""""""""""""""""""""
def server():

    FLASKSERVERFLAG[0] = 1



'''
This will send a shut down request to the server if it is on and
this will close out of tkinter window

'''
def quitEverything():
    if FLASKSERVERFLAG[0] == 1:
        requests.get('http://127.0.0.1:5000/shutdown')
    top.quit()






''''''''''''''''
        recieve()

Description:
    -This function will recive the messages from host and will
     recive timer information. This will also close client if
     the user recives a {quit} flag
'''''''''''''''''

def receive():

    while True:
        try:
            msg = client_socket.recv(BUFSIZ).decode("utf8")

            #-- _TIME_ is a flag that will tell wheter it is time info or message --#
            if msg.find("_TIME_"):
                msg_list.insert(tkinter.END, msg)

                if msg == "{quit}":
                    if FLASKSERVERFLAG[0] == 1:
                        requests.get('http://127.0.0.1:5000/shutdown')
                    client_socket.close()
                    top.quit()
            else:
                t = str(msg).split(" ")
                timeT.set(str(t[1]))
            if msg == "{quit}":
                if FLASKSERVERFLAG[0] == 1:
                    requests.get('http://127.0.0.1:5000/shutdown')
                client_socket.close()
                top.quit()
        except OSError:
            break





"""""""""""
    send()

Description:
    -Send messages to the host
    also tell host when you want to
    quit when you send the "{quit}" flag

"""""""""""


def send(event=None):
    msg = my_msg.get()
    #-- Clears input field -- #
    my_msg.set("")
    client_socket.send(bytes(msg, "utf8"))
    if msg == "{quit}":
        if FLASKSERVERFLAG[0] == 1:
            requests.get('http://127.0.0.1:5000/shutdown')
        client_socket.close()
        top.quit()






"""
Close out of everything
"""
def on_closing(event=None):
    """This function is to be called when the window is closed."""
    if FLASKSERVERFLAG[0] == 1:
        requests.get('http://127.0.0.1:5000/shutdown')
    client_socket.send(bytes("{quit}", "utf8"))
    top.quit()




#-- Wait for the user to inout a IP adress --#
file = open("/Applications/ZoneIn/WritableFiles/HostIP.txt",'r')
HOST = file.readline()
file.close()


#-- Start tkinter --#
top = tkinter.Tk()
top.title("ZoneIn-GroupChat")
top.iconbitmap('/Applications/ZoneIn/Icons/jlogoicon.ico')
top.configure(bg='#FFF8F2')

messages_frame = tkinter.Frame(top)
my_msg = tkinter.StringVar()
my_msg.set("Message")

#--Scroll up and down if messages get too long--#
scrollbar = tkinter.Scrollbar(messages_frame)
#--Display All Messages--#
msg_list = tkinter.Listbox(messages_frame, height=15, width=50, yscrollcommand=scrollbar.set)
scrollbar.pack(side=tkinter.RIGHT, fill=tkinter.Y)
msg_list.pack(side=tkinter.LEFT, fill=tkinter.BOTH)
msg_list.pack()
messages_frame.pack()

#--type your message--#
entry_field = tkinter.Entry(top, textvariable=my_msg)
entry_field.bind("<Return>", send)
entry_field.pack()

#--send your message--#
photo1 = tkinter.PhotoImage(file = r"/Applications/ZoneIn/Icons/jbuttonSM.png")
send_button = tkinter.Button(top, image=photo1, command=send)
send_button.pack()
#-- if your the host leave and shutdown server if normal user just leave the server--#
photo2 = tkinter.PhotoImage(file = r"/Applications/ZoneIn/Icons/jbuttonQ.png")
quit_button = tkinter.Button(top, image=photo2, command=on_closing)
quit_button.pack()
top.protocol("WM_DELETE_WINDOW", on_closing)

#-- Responsible for timer --#

timeT = tkinter.StringVar()
timeT.set("00:00:00")
labtime = tkinter.Label(top,textvariable=timeT,font=("Serif",35))
labtime.configure(bg='#FFF8F2')
labtime.configure(fg='#FF6D4D')
labtime.pack()





PORT = 33000
#--Message Size MAX --#
BUFSIZ = 1024
ADDR = (HOST, PORT)

## -- Create the client socket --#
client_socket = socket(AF_INET, SOCK_STREAM)
#-- Establish the connection between the host -- #
client_socket.connect(ADDR)


#-- Run python flask server --#
server_thread = Thread(target=server)
receive_thread = Thread(target=receive)
server_thread.start()
receive_thread.start()
tkinter.mainloop()
