using UnityEngine;
using System.Collections;

public class movementScript : MonoBehaviour {

	// Use this for initialization
	float moveForward = 0;
	float moveRight = 0;
	float incrementAmount = 2;
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown ("a")) {
			moveRight = moveRight - incrementAmount;
		}
		if (Input.GetKeyUp ("a")) {
			moveRight = moveRight + incrementAmount;
		}

		if (Input.GetKeyDown ("d")) {
			moveRight = moveRight + incrementAmount;
		}
		if (Input.GetKeyUp ("d")) {
			moveRight = moveRight - incrementAmount;
		}

		if (Input.GetKeyDown ("w")) {
			moveForward = moveForward + incrementAmount;
		}
		if (Input.GetKeyUp ("w")) {
			moveForward = moveForward - incrementAmount;
		}
		
		if (Input.GetKeyDown ("s")) {
			moveForward = moveForward - incrementAmount;
		}
		if (Input.GetKeyUp ("s")) {
			moveForward = moveForward + incrementAmount;
		}


		this.transform.Translate(moveRight * Time.deltaTime, 0,  moveForward * Time.deltaTime);
	}
}
