compile:
	javac -classpath ./LeapJava.jar util/Key.java util/KeyboardRow.java model/KeyboardModel.java presenter/KeyboardPresenter.java view/KeyboardView.java MyListener.java InteractionBoxDemo.java;

run:
	java -classpath "./LeapJava.jar:." InteractionBoxDemo

clean:
	rm *.class

all: 
	make compile; make run; make clean;
