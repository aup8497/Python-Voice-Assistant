# speech recognition voice assistant for ubuntu written in python 
# Author: Akshay U Prabhu 
import speech_recognition as sr
from time import ctime
import time
import os
from gtts import gTTS

def speak(text1):
	# print("the text to be converted to speech is " + text1)
	tts = gTTS(text=text1 , lang='en')
	tts.save("command.mp3")
	os.system("mpg321 command.mp3")

def recorder():
	r = sr.Recognizer()
	with sr.Microphone(0) as source:
		r.adjust_for_ambient_noise(source)
	    # r.adjust_for_ambient_noise(source)
		print("Say something")
		audio = r.listen(source)
	print("listening done...")
	data = ""

	try:
		data = r.recognize_google(audio)
		print("you said" + data)
	except sr.UnknownValueError:
		print("Couldnot understand, Try again!!\n")
	except sr.RequestError as e:
		print("coudnot request your results from cloud; {0}".format(e))

	print(" You said " + data )

	return data


def music_handler(text):
	if "play" in text:
		os.system("rhythmbox-client --play")
	elif "pause" in text:
		os.system("rhythmbox-client --pause")
	elif "stop" in text:
		os.system("rhythmbox-client --quit")
	elif "next" in text:
		os.system("rhythmbox-client --next")
	elif "previous" in text:
		os.system("rhythmbox-client --previous")
	elif "lyrics" in text:
		title = os.popen("rhythmbox-client --print-playing").read()
		system.os("firefox https://www.google.co.in/search?q=" + title + " lyrics&btnI=")


def jarvis(data):
	if "how are you" in data:
		speak("I'm fine")
	elif "time" in data:
		speak(ctime())
	elif "terminal" in data:
		os.system("gnome-terminal")
	elif "sublime" in data:
		os.system("subl")
	elif "search" in data:
		data.replace(" ","+")
		print("searching for" + data)
		os.system("firefox https://www.google.co.in/search?client=ubuntu&channel=fs&q="+ data)
	elif "download" in data.lower() and "mp3" in data.lower():
		# removing first and last word i.e download and mp3 to get the name of the song
		# remmoving the first word
		data = data.split(' ',1)[1]
		data = data.rsplit(' ',1)[0]
		os.system("java youtubeSongsPullBugsRemovedfinal "+ data)
	elif "where is" in data:
		data = data.split(" ")
		location = data[-1]
		speak("hold on Akshay , I will show you where " + location + " is.")
		os.system("firefox https://www.google.nl/maps/place/" + location + "/&amp;")
	elif "song" in data:
		music_handler(data)



time.sleep(2)
speak("Hi! Akshay, what can i do for you?")
while(1):
	data = recorder()
	jarvis(data)