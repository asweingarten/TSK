using UnityEngine;
using System.Collections;
using System;

public class textInput : MonoBehaviour {

	private Keyboard keyboard;

	private float mouseX;
	private float mouseY;
	
	private float mouseXDelta;
	private float mouseYDelta;
	
	public float rotationY = 0f;
	public float rotationX = 0f;
	public float viewRange = 90.0f;
	
	public float mouseSensitivity = 10.0f;

	// Use this for initialization
	void Start () {
		mouseX = Input.mousePosition.x;
		mouseY = Input.mousePosition.y;

		keyboard = new Keyboard ();
		keyboard.InputEvent += HandleInputEvent;
		keyboard.ErrorEvent += HandleErrorEvent;

		// DELETE ALL KEYS IN KEYBOARD
		// READ IN 
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
		rotationX += Input.GetAxis ("Mouse X") * 1;
		
		// rotationY -= Input.GetAxis ("Mouse Y") * mouseSensitivity;
		// rotationY = Mathf.Clamp (rotationY, -viewRange, viewRange);
		// Camera.main.transform.Rotate (-rotationY, 0, 0);
		
		transform.localRotation = Quaternion.Euler (90 , rotationX,0);

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
