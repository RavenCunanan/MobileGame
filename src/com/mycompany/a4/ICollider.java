package com.mycompany.a4;

public interface ICollider {

	public boolean collidesWith(GameObject go);
	public void handleCollision(GameObject go);
}
