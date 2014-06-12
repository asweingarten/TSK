JFLAGS = 
JC = javac
CP = -cp lib/jssc.jar:.
DEST = -d classes

.SUFFIXES: .java .class

TSK_PACKAGE_CLASSES = \
			src/TouchKeyStream.java \
			src/TouchKeyHandler.java \
			src/TouchKeyListener.java \
			src/TouchKeyMessage.java

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

tsk: $(TSK_PACKAGE_CLASSES:.java.class)
	jar -cf tsk.jar classes

default: tsk

all: tsk

portLister: $(PORT_PRINTER_CLASSES:.java=.class)

handler: stream $(HANDLER_CLASSES:.java=.class)

stream: message $(STREAM_CLASSES:.java=.class) 

message: $(MESSAGE_CLASSES:.java=.class)


echoDemo: $(ECHO_DEMO:.java=.class)
	

clean:
	$(RM) classes/*.class;
