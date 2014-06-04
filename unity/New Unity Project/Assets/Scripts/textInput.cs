using UnityEngine;
using System.Collections;
using System;

public class textInput : MonoBehaviour {

	private Keyboard keyboard;

	// Use this for initialization
	void Start () {
		keyboard = new Keyboard ();
		keyboard.InputEvent += HandleInputEvent;
		keyboard.ErrorEvent += HandleErrorEvent;
	}

	void HandleErrorEvent (object sender, EventArgs e)
	{
		Debug.Log(String.Format("DANGER, DANGER WILL ROBINSON: {0}", Input.mousePosition.y));
	}

	private void HandleInputEvent (object sender, InputEventArgs e)
	{
		var tryToFindTextMesh = GameObject.Find (String.Format("key_{0}", e.inputCharacter));
		var tm = (TextMesh)tryToFindTextMesh.GetComponent(typeof(TextMesh));

		if (e.isTouchPress) {
			tm.color = new Color(0,0,100);
		} else {
			tm.color = new Color(0,100,0);
		}
	}
	
	// Update is called once per frame
	void Update () {
		string[] keys = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",
						"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\",
						"a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'",
						"z", "x", "c", "v", "b", "n", "m", ",", ".", "/",
						"space"};
		foreach (string s in keys){
			var tryToFindTextMesh = GameObject.Find ("key_" + s);
			var tm = (TextMesh)tryToFindTextMesh.GetComponent(typeof(TextMesh));
			if (Input.GetKeyDown (s)) {
				tm.color = new Color(100,0,0);
			}
			if (Input.GetKeyUp (s)) {
				tm.color = new Color(0,100,0);
			}
		}
	}
}
