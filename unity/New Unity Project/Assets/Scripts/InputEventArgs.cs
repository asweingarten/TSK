using UnityEngine;
using System.Collections;
using System;

public class InputEventArgs : EventArgs{

	public char inputCharacter{ get; private set; }

	public bool isTouchPress{ get; private set;}
	                   
	public InputEventArgs(char inputChar, bool isTouchPress){
		this.inputCharacter = inputChar;
		this.isTouchPress = isTouchPress;
	}


}
