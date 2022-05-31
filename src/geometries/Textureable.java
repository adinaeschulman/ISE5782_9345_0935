package geometries;

import primitives.Point;
import scene.Texture;

public interface Textureable {
    public Texture.ImageCords TextureEmession(Point pt, int nX, int nY);
    public Texture.ImageCords getDims();

}
