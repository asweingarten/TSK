JFLAGS = 
JC = javac
CP = -cp lib/comm-2.0.jar:.
DEST = -d .

.SUFFIXES: .java .class

PORT_PRINTER_CLASSES = \
			SerialPortPrinter.java 

STREAM_CLASSES = \
			TouchKeyStream.java

MESSAGE_CLASSES = \
			TouchKeyMessage.java

HANDLER_CLASSES = \
			TouchKeyHandler.java \
			TouchKeyListener.java

ECHO_DEMO = \
			TouchKeyEcho.java

.java.class:
	javac $(DEST) $(CP) $(JFLAGS) $*.java

default: handler

all: portLister stream handler echoDemo

portLister: $(PORT_PRINTER_CLASSES:.java=.class)

handler: stream $(HANDLER_CLASSES:.java=.class)

stream: message $(STREAM_CLASSES:.java=.class) 

message: $(MESSAGE_CLASSES:.java=.class)


echoDemo: $(ECHO_DEMO:.java=.class)
	

clean:
	$(RM) *.class;
