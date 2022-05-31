package scene;

import primitives.Color;

public class TextureScene extends Scene {
    Texture tx ;
    public TextureScene (SceneBuilder senceName, Texture x){
        super(senceName);
        tx = x ; 
        
    }
   // @Override
    public Color getBg(int i , int j , int px, int py ){
        return tx.getColor(i,j,px,py) ;
    }

    
}
