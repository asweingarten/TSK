using UnityEngine;
using System.Collections;

public class CamMouse : MonoBehaviour {

	// Use this for initialization

	public static bool lookAtPanel;

	private float mouseX;
	private float mouseY;

	private float mouseXDelta;
	private float mouseYDelta;

	public float rotationY = 0f;
	public float rotationX = 0f;
	public float viewRange = 90.0f;

	public float mouseSensitivity = 10.0f;

	float Speed = 4;
	GameObject forceField;
	bool ePressed;
	int eCount = 0;

	void Start () {
		mouseX = Input.mousePosition.x;
		mouseY = Input.mousePosition.y;

		forceField = GameObject.Find ("Input1");
		ePressed = false;
	}
	
	// Update is called once per frame
	void Update () {
		rotationX += Input.GetAxis ("Mouse X") * mouseSensitivity;

		rotationY -= Input.GetAxis ("Mouse Y") * mouseSensitivity;
		rotationY = Mathf.Clamp (rotationY, -viewRange, viewRange);
		// Camera.main.transform.Rotate (-rotationY, 0, 0);

		Camera.main.transform.localRotation = Quaternion.Euler (rotationY, rotationX,0);
		if (Input.GetKeyDown ("e")) {
			ePressed = true;
			eCount++;
			if (eCount > 11) ePressed = false;
		}

		if (lookAtPanel) {
			transform.LookAt(forceField.transform);
			// transform.position += transform.right * Speed * Time.deltaTime;
		}
	}
}