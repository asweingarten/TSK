using UnityEngine;
using System.Collections;

public class cameraScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		//Debug.Log (this.transform.eulerAngles.x + "\t" + this.transform.position.x + "\t" + this.transform.position.z);
		if (Input.GetKeyUp ("n")) {
			Debug.Log ("SPAWN OBJECT WITH:\t" + this.transform.eulerAngles.x + "\t" + this.transform.position.x + "\t" + this.transform.position.z);
			//tm.color = new Color(0,100,0);
			var cube = GameObject.CreatePrimitive(PrimitiveType.Cube);
			//cube.transform.localScale.x *= 10;
			cube.name = "cube_new";
			// color is controlled like this
			cube.renderer.material.color = Color.red; // for example
			cube.transform.position = new Vector3(this.transform.position.x, 11, this.transform.position.z + 4);
			//cube.transform.
			cube.transform.LookAt(this.transform.position);
		}
	}
}
