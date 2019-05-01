package ar.ed.itba.utils.filters.mask.weight.heat;

public class AnisotropicFilter extends HeatFilter {
	
	private final AnisotropicG g;
	private final double sigma;
	
	public AnisotropicFilter(final AnisotropicG g, final int t, final double delta, final double sigma) {
		super(delta, t);
		this.g = g;
		this.sigma = sigma;
	}
  
  @Override
  protected double getCij(double dIij) {
	  switch (g){
      case LECLERC:
        return leclerc(dIij);
      case LORENTZ:
      default:
        return lorentz(dIij);
    }
  }
  
  private double lorentz(double x){
		return 1.0 / ((x * x / (sigma * sigma))+1);
	}
	
	private double leclerc(double x){
		return Math.pow(Math.E, - (x * x / (sigma * sigma)));
	}
  
  @Override
  protected boolean hasToNormalize() {
    return false;
  }
}
