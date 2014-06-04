using UnityEngine;
using System.Collections;
using System.IO.Ports;
using System;
using System.Text;

public class Keyboard {
	private SerialPort keyboardPort;

	private void OnDataReceived(object sender, SerialDataReceivedEventArgs e) {
		int keyCode = keyboardPort.ReadByte ();
		int isTouchPress = keyboardPort.ReadByte ();
		if (isTouchPress < 0) {
			OnErrorEvent();
			return;
		}
		OnInputEvent (Convert.ToChar(keyCode), Convert.ToBoolean(isTouchPress));
	}

	private void OnErrorReceived(object sender, SerialErrorReceivedEventArgs e) {
		OnErrorEvent ();
	}

	private void OnPinChanged(object sender, SerialPinChangedEventArgs e) {
		OnPinChangedEvent ();
	}

	public Keyboard() {
		keyboardPort = new SerialPort (string.Empty, 9600);
		keyboardPort.DataReceived += OnDataReceived;
		keyboardPort.ErrorReceived += OnErrorReceived;
		keyboardPort.PinChanged += OnPinChanged;
	}

	public event EventHandler<InputEventArgs> InputEvent;

	public event EventHandler ErrorEvent;

	public event EventHandler PinChangedEvent;

	private void OnInputEvent(char inputCharacter, bool isTouchPress) {
		if (InputEvent != null) {
			InputEvent(this, new InputEventArgs(inputCharacter, isTouchPress));
		}
	}

	private void OnErrorEvent() {
		if (ErrorEvent != null) {
			ErrorEvent(this, null);
		}
	}

	private void OnPinChangedEvent() {
		if (PinChangedEvent != null) {
			PinChangedEvent(this, null);
		}
	}

}
