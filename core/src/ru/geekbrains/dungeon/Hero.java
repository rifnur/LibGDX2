package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private ProjectileController projectileController;
    private Vector2 position;
    private TextureRegion texture;
    private float speed;
    private float angle;
    private float scale;
    private float angleWeapon;
    private boolean activeQ=false;
    int width;
    int height;


    public void isActiveQ() {
        activeQ = true;
    }

    public void deactivateQ() {
        activeQ = false;
    }

    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 100);
        this.speed = 240.0f;
        this.scale = 1.0f;
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
    }

    public void update(float dt) {
        Smena(activeQ);
        Move(dt);

        if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) & (activeQ)) {
            projectileController.activate(position.x , position.y , position.x * scale * MathUtils.cosDeg(angle),position.y * scale * MathUtils.sinDeg(angle+5));
            projectileController.activate(position.x , position.y , position.x * scale * MathUtils.cosDeg(angle),position.y * scale * MathUtils.sinDeg(angle-5));

            }
        if ((Gdx.input.isKeyJustPressed(Input.Keys.SPACE))& !(activeQ)) {
//            projectileController.activate(position.x, position.y, 200, 10);
            projectileController.activate(position.x , position.y , position.x * scale * MathUtils.cosDeg(angle),position.y * scale * MathUtils.sinDeg(angle+5));
            projectileController.activate(position.x , position.y , position.x * scale * MathUtils.cosDeg(angle),position.y * scale * MathUtils.sinDeg(angle-5));


        }

        if (position.x < 0.0f+20) {position.x = 0.0f+20; }
        if (position.x > Gdx.graphics.getWidth()-20) {position.x = Gdx.graphics.getWidth()-20;}
        if (position.y < 0.0f+20) { position.y = 0.0f+20;}
        if (position.y > Gdx.graphics.getHeight()-20) {position.y = Gdx.graphics.getHeight()-20;}
    }
    public void Smena(boolean activeQ1)
    {
        if ((Gdx.input.isKeyJustPressed(Input.Keys.Q)) & (activeQ1=true))
        {deactivateQ();}
        else {isActiveQ();}

    }

    public void Move(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed * dt;
            angle = 180.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed * dt;
            angle = 0.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed * dt;
            angle = 90.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed * dt;
            angle = 270.0f;
        }
    }



    public void render(SpriteBatch batch) {
//        batch.draw(texture, position.x - 20, position.y - 20);
        batch.draw(texture, position.x - 20, position.y - 20, 20, 20, 40, 40, 1, 1, angle);

    }

}
