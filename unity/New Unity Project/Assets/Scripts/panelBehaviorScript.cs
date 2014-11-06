using UnityEngine;
using System.Collections;

public class panelBehaviorScript : MonoBehaviour {

	GameObject user;
	GameObject forceField;
	TextMesh panelText;
	// var panelText;

	// Use this for initialization
	void Start () {
		user = GameObject.Find ("User");
		forceField = GameObject.Find ("ForceField");

		var tryToFindTextMesh = GameObject.Find ("Input1");
		panelText = (TextMesh)tryToFindTextMesh.GetComponent(typeof(TextMesh));

		// panelText = (TextMesh)  GameObject.Find ("NewText");
	}
	
	// Update is called once per frame
	void Update () {
		float distance =  + Mathf.Sqrt(Mathf.Pow ((user.transform.position.x - transform.position.x), 2) + Mathf.Pow((user.transform.position.z - transform.position.z), 2));

		if (distance < 10) {

			// string[] keys = {"!", "@", "#", "$", "%"};
			// Debug.Log(distance);

			string[] keys = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",
				"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\",
				"a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'",
				"z", "x", "c", "v", "b", "n", "m", ",", ".", "/",
				"space"};

			string[] keys2 = {"1", "2", "3", "4", "5"};

			// if (Input.GetKeyDown (KeyCode.LeftShift)) {
				
				if (Input.GetKeyUp ("1")) {
					Debug.Log("here");
					panelText.text = panelText.text + "!";
				}
				if (Input.GetKeyUp ("2")) {
					panelText.text = panelText.text + "@";
				}
				if (Input.GetKeyUp ("3")) {
					panelText.text = panelText.text + "#";
				}
				if (Input.GetKeyUp ("4")) {
					panelText.text = panelText.text + "$";
				}
				if (Input.GetKeyUp ("5")) {
					panelText.text = panelText.text + "%";
				}

				/*

				foreach (string s in keys){
					if (Input.GetKeyUp (s)) {
						if (Input.GetKeyUp (KeyCode.LeftShift)) {
							panelText.text = panelText.text + s;
							CamMouse.lookAtPanel = !CamMouse.lookAtPanel;
						}
						panelText.text = panelText.text + s;
					}
				}
				*/
			// }




			/*
			if (Input.GetKeyDown ("e")) {
				Debug.Log ("INTERACTION ALLOWED");
				// panelText.text = panelText.text + "E";
				panelText.text = panelText.text + "E";
				forceField.transform.Translate(0, 3, 0);
			}
			*/
		}
	}
}
