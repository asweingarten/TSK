using UnityEngine;
using System.Collections;

public class CamMouse : MonoBehaviour {

	// Use this for initialization

	private float mouseX;
	private float mouseY;

	private float mouseXDelta;
	private float mouseYDelta;

	void Start () {
		mouseX = Input.mousePosition.x;
		mouseY = Input.mousePosition.y;
	}
	
	// Update is called once per frame
	void Update () {
		var easeFactor = 10f;
		mouseXDelta = Input.mousePosition.x - mouseX;
		mouseYDelta = Input.mousePosition.y - mouseY;

		var rotateAmount = - mouseYDelta * easeFactor * Time.deltaTime;
		this.transform.Rotate (rotateAmount, 0, 0);

		// var rotateAmountLR = mouseXDelta * easeFactor * Time.deltaTime;
		// this.transform.Rotate (0, rotateAmountLR, 0);

		mouseX = Input.mousePosition.x;
		mouseY = Input.mousePosition.y;

		// Debug.Log("Cam Pos: " + Input.mousePosition.y);
	}
}