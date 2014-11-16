using UnityEngine;


public class Key {
	private TextMesh tm;
	private string id;
	
	private float keyWidth;
	private float keySpaceWidth;
	
	private GameObject cube;
	private TextMesh textMesh;
	
	public Key(GameObject parent, string id, float positionOffset){
		Debug.Log("CREATING OBJECT " + id);
		cube = GameObject.CreatePrimitive(PrimitiveType.Cube);
		cube.name = ("cube_new_" + id);
		
		float positionX = parent.transform.position.x + 3;
		float positionY = 11;
		float positionZ = parent.transform.position.z + 6 - (positionOffset);
		
		cube.transform.localPosition = new Vector3(positionX, positionY, positionZ);
		cube.renderer.sharedMaterial.color = Color.cyan;
		cube.transform.localScale = new Vector3(0.5f,0.5f,0.5f);
		cube.transform.parent = parent.transform;
		
		Font ArialFont = (Font)Resources.GetBuiltinResource (typeof(Font), "Arial.ttf");
		GameObject text = new GameObject();
		text.transform.parent = cube.transform;
		text.transform.localPosition = new Vector3(0.5f,0.3f,0.3f);
		text.transform.localRotation = Quaternion.Euler (90,90,0);
		text.transform.localScale = new Vector3(1, 1, 1);
		TextMesh textMesh = (TextMesh)text.AddComponent("TextMesh");
		textMesh.text = id;
		textMesh.color = Color.red;
		textMesh.font = ArialFont;
		textMesh.renderer.sharedMaterial = ArialFont.material;
		textMesh.fontSize = 12;
		
		this.tm = textMesh;
		this.id = id;
		this.keyWidth = 10;
		this.keySpaceWidth = 0;
	}
	
	public Key(TextMesh tm, string id, float keyWidth, float keySpaceWidth){
		this.tm = tm;
		this.id = id;
		this.keyWidth = keyWidth;
		this.keySpaceWidth = keySpaceWidth;
	}
	
	public void setColorBlue(){
		tm.color = new Color(0,0,100);
	}
	
	public void setColorGreen(){
		tm.color = new Color(0,100,0);
	}
	
	public void setColorRed(){
		tm.color = new Color(100,0,0);
	}
	
	public float getKeyWidth(){
		return this.keyWidth;
	}
	
	public float getKeySpaceWidth(){
		return this.keySpaceWidth;
	}
}
