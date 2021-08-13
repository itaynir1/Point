
/**
 * point class - this class wil generate points in (x,y) format for the user.
 * Pay attention to the background methods characters that using polar system funct(vectors).
 *  @author Itay Nir
 */
public class Point {

    private double _radius, _alpha ;

    private final double DEFAULT_VAL = 0 , DEGREES_PI_DIV2= 90 , ROUND_VAR = 10000, TO_DEGREES = 180 / Math.PI;

    //custom Point constructor:
    /**
     *Construct a point.
     *if one of the parameters is negative then it should be initialized to zero.
     * @param x  The x coordinate.
     * @param y  The y coordinate.
     */
    public Point( double x, double y)
    {

        x = (x>DEFAULT_VAL)? x : DEFAULT_VAL;
        y = (y>DEFAULT_VAL)? y : DEFAULT_VAL;
        _radius =  Math.sqrt(x * x + y * y);

        if(x>DEFAULT_VAL)
            _alpha = Math.atan(y/x) * (TO_DEGREES);  //save the alpha character in degreeף
        else _alpha = DEGREES_PI_DIV2;               //when x=0, radius is y.


    }

    //this is the copy constructor for class point.
    /**
     *Copy constructor for Point
     *Construct a point with the same coordinates as other point
     * @param other The point object from which to construct the new point.
     *
     */
    public Point(Point other) {
        if (other != null) { //check the initialization of  the given object
            _radius = other._radius;
            _alpha = other._alpha;
        }
    }

    //getters and setters:
    /**
     *Returns the x coordinate of the point.
     * @return The x coordinate of the point.
     */
    public double getX()
    {
        return Round(_radius * Math.cos(_alpha / TO_DEGREES));
    }

    /**
     *Returns the y coordinate of the point.
     * @return The y coordinate of the point.
     */

    public double getY()

    {
        return Round (_radius * Math.sin(_alpha / TO_DEGREES)) ;  }
    /**
     * Sets the x coordinate of the point
     *if a negative number is received
     * Than the x coordinate does not change.
     * @param num The new x coordinate.
     */
    public void setX(double num)
    {
        if(num>=DEFAULT_VAL)
        {
            final double Y0 = getY();

            _radius = Math.sqrt(num*num + Y0*Y0);
            if(num>DEFAULT_VAL)   //this condition prevents the points from going out of the interface.
                _alpha = Math.atan(Y0/num) * TO_DEGREES;
            else _alpha =  DEGREES_PI_DIV2;
        }
    }
    /**
     * Sets the y coordinate of the point
     *if a negative number is received
     * Than the y coordinate does not change.
     * @param num The new y coordinate.
     */
    public void setY(double num)
    {
        if(num>=DEFAULT_VAL)
        {
            final  double X1 = getX();

            _radius = Math.sqrt((X1*X1) + num*num);
            if(X1>DEFAULT_VAL)                 //this condition keep the interface and prevents creating points out of the first quarter.
                _alpha = Math.atan(num/X1) * TO_DEGREES;
            else _alpha =  DEGREES_PI_DIV2;

        }
    }


    // the methods:

    /**
     * Check if this point equals to other point.
     * @param other The point to be compared with this point
     * @return true if this point equals other point
     */
    public boolean equals(Point other)
    {
        return (_radius == other._radius && _alpha == other._alpha);
    }

    /**
     *Check if this point is above a above other point.
     * @param other The point to be compared with this point
     * @return true if this point is above other point
     */
    public boolean isAbove(Point other)
    {
        return ( getY() > other.getY());
    }

    /**
     * Check if this point is below a received point.
     * @param other The point to check if this point is under
     * @return true if this point is under other point
     */
    public boolean isUnder(Point other)
    {
        return other.isAbove(this);
    }

    /**
     * Check if this point is left of a received point.
     * @param other The point to check if this point is left of
     * @return true if this point is on the left of other point
     */
    public boolean isLeft(Point other)
    {
        return ( getX() < other.getX() );
    }

    /**
     * Check if this point is right of a received point.
     * @param other The point to check if this point is right of
     * @return true if this point is on the right of other point
     */
    public boolean isRight(Point other)
    {
        return other.isLeft(this);
    }

    /**
     * Calculate the distance between this point and a given point.
     * @param p The point to calculate the distance from
     * @return The distance between this and p points.
     */
    public double distance(Point p)
    {
        return Round( Math.sqrt( Math.pow(( p.getY() - getY() ),2) + Math.pow((p.getX() - getX()),2)));
    }

    /**
     * Moves the x coordinate by dX and the y coordinate by dY.
     * if movement moves the point  outside the first quadrant than the
     * point will remain at the same place and not move.
     * @param dx The amount to move in the x direction.
     * @param dy The amount to move in the y direction.
     */
    public void move (double dx, double dy) {
        double x0 = getX(), y0 = getY();
        if (x0 + dx >= DEFAULT_VAL && y0 + dy >= DEFAULT_VAL)
        {
            setX(x0 + dx);
            setY(y0 + dy);
        }
    }

    /**
     *Private method to round numbers 4 digits after the point.(ROUND_VAR is 10000)
     * @param roundThis The double we would like to round
     * @return The same double with only 4 digits after the point
     */
    private double Round (double roundThis)
    {
        return  Math.round((roundThis)*ROUND_VAR)  /(double)ROUND_VAR ;
    }


    /**
     *
     * Returns a string representation  of this point.
     * @return String representation  of this point.
     */
    public String toString()
    {
        return "(" + getX() + "," + getY() + ")";
    }
}
