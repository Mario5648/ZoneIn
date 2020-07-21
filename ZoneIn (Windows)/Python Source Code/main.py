import time
from flask import Flask, redirect,url_for,request,render_template
import noFunAIPY
import ytBlock
import pickle as cPickle
import requests

#--This is to notify wheter or not to send a shutdown request to the PythonServer--#
with open('C:/ZoneIn/Python/noFunAI.pkl','rb') as file:
    noFunAI = cPickle.load(file)

blockWebTags = []
blockYTTags = []
file = open('C:/ZoneIn/WritableFiles/blockTags.txt','r')
for tag in file.readlines():
    blockWebTags.append(str(tag).strip("\n"))
file.close()

file = open('C:/ZoneIn/WritableFiles/blockTagsYT.txt','r')
for tag in file.readlines():
    blockYTTags.append(str(tag).strip("\n"))
file.close()



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

app = Flask(__name__)

@app.route("/")
def home():
    return render_template("index.html")

def shutdown_server():
    func = request.environ.get('werkzeug.server.shutdown')
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()

@app.route('/shutdown')
def shutdown():
    shutdown_server()
    return 'Server shutting down...'

@app.route("/<URL>")
def decide(URL):
    #if the URL contains the YT code
    if URL[0] == "Y" and URL[1]=="T":
        code = ytBlock.decide(URL[2:],blockYTTags)
    else:
        code = noFunAIPY.decide(URL,noFunAI,blockWebTags)
    return str(code)

app.run()
