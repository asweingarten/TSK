using UnityEngine;
using System.Collections;

public class MovementScript : MonoBehaviour {

	// Use this for initialization
	public float rotationX = 0f;
	// public float mouseSensitivity = 10.0f;
	private GameObject keyboard;


	float moveForward = 0;
	float moveRight = 0;
	float incrementAmount = 20;
	void Start () {
		keyboard = GameObject.Find ("ScriptKeyboard");
//		user = GameObject.Find ("User");
	
	}
	
	// Update is called once per frame
	void Update () {
		rotationX += Input.GetAxis ("Mouse X");

		keyboard.transform.localRotation = Quaternion.Euler (0, rotationX - 90, 0);

		// Debug.Log (rotationX);
		var sinOfAngle = Mathf.Sin((rotationX * Mathf.PI)/180);
		var cosOfAngle = Mathf.Cos((rotationX * Mathf.PI)/180);

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

		float xMovement = moveRight * Time.deltaTime * cosOfAngle + moveForward * Time.deltaTime * sinOfAngle;
		float zMovement = moveForward * Time.deltaTime * cosOfAngle - moveRight * Time.deltaTime * sinOfAngle;

		// float xMovement = moveRight * Time.deltaTime * sinOfAngle + moveForward * Time.deltaTime * cosOfAngle;
		// float zMovement = moveForward * Time.deltaTime * sinOfAngle + moveRight * Time.deltaTime * cosOfAngle;
		this.transform.Translate(xMovement, 0, zMovement);
	}
}
