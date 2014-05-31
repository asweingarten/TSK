using UnityEngine;
using System.Collections;

public class textInput : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
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


		/*
		 tryToFindTextMesh = GameObject.Find ("key_s");
		 tm = (TextMesh)tryToFindTextMesh.GetComponent(typeof(TextMesh));
		if (Input.GetKeyDown ("s")) {
			tm.color = new Color(100,0,0);
		}
		if (Input.GetKeyUp ("s")) {
			tm.color = new Color(0,100,0);
		}
		*/
	}
}
