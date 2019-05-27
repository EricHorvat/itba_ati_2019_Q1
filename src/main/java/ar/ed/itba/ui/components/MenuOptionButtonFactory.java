package ar.ed.itba.ui.components;

import ar.ed.itba.ui.frames.EditableImageFrame.ApplyActionListener;
import ar.ed.itba.ui.listeners.button.edit.effect.*;
import ar.ed.itba.ui.listeners.button.file.effect.OpenFixedFileButtonListener;
import ar.ed.itba.ui.listeners.button.file.effect.ResetFileButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.tp1.*;
import ar.ed.itba.ui.listeners.button.filter.effect.tp2.*;
import ar.ed.itba.ui.listeners.button.filter.effect.tp2.gradient.KirshFilterButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.tp2.gradient.PrewitFilterButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.tp2.gradient.SobelFilterButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.tp2.gradient.TP2P5AFilterButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.tp2.laplacian.LaplacianFilterButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.tp2.laplacian.LoGFilterButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.tp3.*;
import ar.ed.itba.ui.listeners.button.filter.effect.tp4.HarrisButtonListener;
import ar.ed.itba.ui.listeners.button.generate.effect.*;
import ar.ed.itba.ui.listeners.button.file.effect.OpenFileButtonListener;
import ar.ed.itba.ui.listeners.button.file.effect.SaveFileButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;

import static ar.ed.itba.utils.filters.mask.gradient.GradientFilterType.*;
import static ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicG.LECLERC;
import static ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicG.LORENTZ;

public final class MenuOptionButtonFactory {
	
	private MenuOptionButtonFactory() {}
	
	/* FILE */
	
	public static JButton openFileMenuOptionButton(JTextField filePathField){
		JButton button = applyButton(new OpenFileButtonListener(filePathField));
		button.setText("Open file");
		return button;
	}
	
	public static JButton resetFileMenuOptionButton(){
		JButton button = applyButton(new ResetFileButtonListener());
		button.setText("Reset file");
		return button;
	}
  
  public static JButton openSynteticVideoFileMenuOptionButton(){
    JButton button = applyButton(new OpenFixedFileButtonListener("./res/video/a1.ppm"));
    button.setText("Open Video file");
    return button;
  }
  
  public static JButton openTESTFileMenuOptionButton(){
    JButton button = applyButton(new OpenFixedFileButtonListener("./res/TEST.PGM"));
    button.setText("Open Test file");
    return button;
  }
	
	public static JButton openLenaFileMenuOptionButton(){
		JButton button = applyButton(new OpenFixedFileButtonListener("./res/LENA.RAW"));
		button.setText("Open Lena file");
		return button;
	}
	
	public static JButton saveFileMenuOptionButton(JTextField fileNameField){
		JButton button = new JButton("Save file");
		button.addActionListener(new SaveFileButtonListener(fileNameField));
		return button;
	}
	
	/* END FILE*/
	/* EDIT */
	
	public static JButton editPixelMenuOptionButton(JTextField colorField){
		return applyButton(new EditPixelButtonListener(colorField));
	}
	
	public static JButton addImageMenuOptionButton(JTextField filePathField){
		return applyButton(new AddImageButtonListener(filePathField));
	}
	
	public static JButton lessImageMenuOptionButton(JTextField filePathField){
		return applyButton(new LessImageButtonListener(filePathField));
	}
	
	public static JButton dynamicRangeMenuOptionButton(JTextField scalarField){
		return applyButton(new DynamicRangeButtonListener(scalarField));
	}
	
	public static JButton gammaPowerMenuOptionButton(JTextField gammaField){
		return applyButton(new GammaPowerButtonListener(gammaField));
	}
	
	public static JButton negativeMenuOptionButton(){
		return applyButton(new NegativeButtonListener());
	}
	
	public static JButton contrastMenuOptionButton(JTextField r1xField, JTextField r1yField, JTextField r2xField,
												   JTextField r2yField){
		return applyButton(new ContrastButtonListener(r1xField, r1yField, r2xField, r2yField));
	}
	
	public static JButton thresholdMenuOptionButton(JTextField tField){
		return applyButton(new ThresholdButtonListener(tField));
	}

	public static JButton globalThresholdMenuOptionButton(){
		return applyButton(new GlobalThresholdButtonListener());
	}

	public static JButton otsuThresholdMenuOptionButton(){
		return applyButton(new OtsuThresholdButtonListener());
	}
	
	public static JButton equalizationMenuOptionButton(){
		return applyButton(new EqualizationButtonListener());
	}
	
	public static JButton applyGaussianMenuOptionButton(JTextField percentageField, JTextField sigmaField, JTextField muField){
		return applyButton(new ApplyGaussianButtonListener(percentageField,sigmaField, muField));
	}
	
	public static JButton applyRayleighMenuOptionButton(JTextField percentageField, JTextField phiField){
		return applyButton(new ApplyRayleighButtonListener(percentageField, phiField));
	}
	
	public static JButton applyExponentialMenuOptionButton(JTextField percentageField, JTextField lambdaField){
		return applyButton(new ApplyExponentialButtonListener(percentageField, lambdaField));
	}
	
	public static JButton applySaltAndPepperMenuOptionButton(JTextField sField){
		return applyButton(new ApplySaltAndPepperButtonListener(sField));
	}
	
	/* END EDIT */
	/* GENERATE */
	
	public static JButton generateCircleMenuOptionButton(JTextField radiusField){
		return generateButton(new GenerateCircleButtonListener(radiusField));
	}
	
	public static JButton generateSquareMenuOptionButton(JTextField sideField){
		return generateButton(new GenerateSquareButtonListener(sideField));
	}
	
	public static JButton generateGrayDegradeMenuOptionButton(JTextField widthField, JTextField heigthField){
		return generateButton(new GenerateGrayDegradeButtonListener(widthField, heigthField));
	}
	
	public static JButton generateColorDegradeMenuOptionButton(JTextField color1Field, JTextField color2Field, JTextField widthField, JTextField heigthField){
		return generateButton(new GenerateColorDegradeButtonListener(color1Field, color2Field, widthField, heigthField));
	}
	
	public static JButton generateHSVMenuOptionButton(){
		return generateButton(new GenerateHSVButtonListener());
	}
	
	public static JButton generateGrayHistogramMenuOptionButton(){
		return generateButton(new GenerateGrayHistogramButtonListener());
	}
	
	public static JButton generateGaussianMenuOptionButton(JTextField percentageField, JTextField sigmaField, JTextField muField){
		return generateButton(new GenerateGaussianButtonListener(percentageField, sigmaField, muField));
	}
	
	public static JButton generateRayleighMenuOptionButton(JTextField percentageField, JTextField phiField){
		return generateButton(new GenerateRayleighButtonListener(percentageField, phiField));
	}
  
  public static JButton generateExponentialMenuOptionButton(JTextField percentageField, JTextField lambdaField){
    return generateButton(new GenerateExponentialButtonListener(percentageField, lambdaField));
  }
  
  public static JButton generateHoughLinesMenuOptionButton(JTextField circumferenceCount,
														   JTextField fromTheta, JTextField toTheta, JTextField thetaIntervals,
														   JTextField fromPhi, JTextField toPhi, JTextField phiIntervals){
    return generateButton(new GenerateHoughLinesButtonListener(circumferenceCount, fromTheta, toTheta, thetaIntervals, fromPhi, toPhi, phiIntervals));
  }
  
  public static JButton generateHoughCirclesMenuOptionButton(JTextField circumferenceCount,
  															 JTextField fromA, JTextField toA, JTextField aIntervals,
															 JTextField fromB, JTextField toB, JTextField bIntervals,
															 JTextField fromR, JTextField toR, JTextField rIntervals) {
    return generateButton(new GenerateHoughCirclesButtonListener(circumferenceCount, fromA, toA, aIntervals, fromB, toB, bIntervals, fromR,
			toR, rIntervals));
  }
	
	/* END GENERATE */
	/* FILTER */

	public static JButton mediaFilterMenuOptionButton(JTextField maskSideField){
		return generateButton(new MediaFilterButtonListener(maskSideField));
	}
	
	public static JButton medianFilterMenuOptionButton(JTextField maskSideField){
		return generateButton(new MedianFilterButtonListener(maskSideField));
	}
	
	public static JButton ponderedMedianFilterMenuOptionButton(){
		return generateButton(new PonderedMedianFilterButtonListener());
	}
	
	public static JButton gaussianFilterMenuOptionButton(JTextField maskSideField){
		return generateButton(new GaussianFilterButtonListener(maskSideField));
	}
	
	public static JButton bilateralFilterMenuOptionButton(JTextField maskSideField, JTextField sigmaRField){
		return generateButton(new BilateralFilterButtonListener(maskSideField, sigmaRField));
	}
  
  public static JButton anisotropicLeclercFilterMenuOptionButton(JTextField deltaField, JTextField sigmaField,
                                                                 JTextField tField){
    JButton button = generateButton(new AnisotropicFilterButtonListener(deltaField, sigmaField, tField, LECLERC));
    button.setText("Apply Leclerc");
    return button;
  }
  
  public static JButton anisotropicLorentzFilterMenuOptionButton(JTextField deltaField, JTextField sigmaField,
                                                                 JTextField tField){
    JButton button = generateButton(new AnisotropicFilterButtonListener(deltaField, sigmaField, tField, LORENTZ));
    button.setText("Apply Lorentz");
    return button;
  }
  
  public static JButton isotropicFilterMenuOptionButton(JTextField deltaField, JTextField tField){
    return generateButton(new IsotropicFilterButtonListener(deltaField, tField));
  }
  
  public static JButton cannyFilterMenuOptionButton(JTextField deltaField, JTextField tField){
    return generateButton(new CannyFilterButtonListener(deltaField, tField));
  }
  
  public static JButton susanFilterMenuOptionButton(JTextField epsField){
    return generateButton(new SusanFilterButtonListener(epsField));
  }
  
  public static JButton activeContourFilterMenuOptionButton(JTextField nField){
    return applyButton(new ActiveContourFilterButtonListener(nField));
  }
  
  public static JButton activeContourVideoMenuOptionButton(JTextField frameField){
    return applyButton(new ActiveContourVideoButtonListener(frameField));
  }

  public static JButton setOuterActiveContourFilterMenuOptionButton(){
    JButton b = generateButton(new SetOuterActiveContourFilterButtonListener());
    b.setText("Set Outer");
    return b;
    
  }
  
  public static JButton setInnerActiveContourFilterMenuOptionButton(){
    JButton b = generateButton(new SetInnerActiveContourFilterButtonListener());
    b.setText("Set Inner");
    return b;
  }
  
  public static JButton setParamsActiveContourFilterMenuOptionButton(JTextField nMax){
    JButton b = generateButton(new SetActiveContourFilterButtonListener(nMax));
    b.setText("Set Max Iterations");
    return b;
  }
  
  public static JButton harrisMenuOptionButton(JTextField toleranceField){
    return generateButton(new HarrisButtonListener(toleranceField));
  }
  
  public static JButton highlightBorderFilterMenuOptionButton(JTextField maskSideField){
		return generateButton(new HighlightBorderFilterButtonListener(maskSideField));
	}
	
	public static JButton prewitMaxFilterMenuOptionButton(){
		JButton button = generateButton(new PrewitFilterButtonListener(MAX));
		button.setText("Max");
		return button;
	}
	
	public static JButton prewitAvgFilterMenuOptionButton(){
		JButton button = generateButton(new PrewitFilterButtonListener(AVG));
		button.setText("Average");
		return button;
	}
	
	public static JButton prewitMod2FilterMenuOptionButton(){
		JButton button = generateButton(new PrewitFilterButtonListener(MOD));
		button.setText("Mod2");
		return button;
	}
	
	public static JButton prewitMinFilterMenuOptionButton(){
		JButton button = generateButton(new PrewitFilterButtonListener(MIN));
		button.setText("Min");
		return button;
	}
	
	public static JButton prewitHorizontalFilterMenuOptionButton(){
		JButton button = generateButton(new PrewitFilterButtonListener(HOR));
		button.setText("Horizontal");
		return button;
	}
	
	public static JButton prewitVerticalFilterMenuOptionButton(){
		JButton button = generateButton(new PrewitFilterButtonListener(VER));
		button.setText("Vertical");
		return button;
	}
  
  public static JButton prewitG45FilterMenuOptionButton(){
    JButton button = generateButton(new PrewitFilterButtonListener(G45));
    button.setText("45");
    return button;
  }
  
  public static JButton prewitG135FilterMenuOptionButton(){
    JButton button = generateButton(new PrewitFilterButtonListener(G135));
    button.setText("135");
    return button;
  }
	
  public static JButton sobelMaxFilterMenuOptionButton(){
    JButton button = generateButton(new SobelFilterButtonListener(MAX));
    button.setText("Max");
    return button;
  }
	
	public static JButton sobelAvgFilterMenuOptionButton(){
		JButton button = generateButton(new SobelFilterButtonListener(AVG));
		button.setText("Average");
		return button;
	}
	
	public static JButton sobelMod2FilterMenuOptionButton(){
		JButton button = generateButton(new SobelFilterButtonListener(MOD));
		button.setText("Mod2");
		return button;
	}
	
	public static JButton sobelMinFilterMenuOptionButton(){
		JButton button = generateButton(new SobelFilterButtonListener(MIN));
		button.setText("Min");
		return button;
	}
	
	public static JButton sobelHorizontalFilterMenuOptionButton(){
		JButton button = generateButton(new SobelFilterButtonListener(HOR));
		button.setText("Horizontal");
		return button;
	}
	
	public static JButton sobelVerticalFilterMenuOptionButton(){
		JButton button = generateButton(new SobelFilterButtonListener(VER));
		button.setText("Vertical");
		return button;
	}
  
  public static JButton sobelG45FilterMenuOptionButton(){
    JButton button = generateButton(new SobelFilterButtonListener(G45));
    button.setText("45");
    return button;
  }
  
  public static JButton sobelG135FilterMenuOptionButton(){
    JButton button = generateButton(new SobelFilterButtonListener(G135));
    button.setText("135");
    return button;
  }
	
	public static JButton kirshMaxFilterMenuOptionButton(){
		JButton button = generateButton(new KirshFilterButtonListener(MAX));
		button.setText("Max");
		return button;
	}
	
	public static JButton kirshAvgFilterMenuOptionButton(){
		JButton button = generateButton(new KirshFilterButtonListener(AVG));
		button.setText("Average");
		return button;
	}
	
	public static JButton kirshMod2FilterMenuOptionButton(){
		JButton button = generateButton(new KirshFilterButtonListener(MOD));
		button.setText("Mod2");
		return button;
	}
	
	public static JButton kirshMinFilterMenuOptionButton(){
		JButton button = generateButton(new KirshFilterButtonListener(MIN));
		button.setText("Min");
		return button;
	}
	
	public static JButton kirshHorizontalFilterMenuOptionButton(){
		JButton button = generateButton(new KirshFilterButtonListener(HOR));
		button.setText("Horizontal");
		return button;
	}
	
	public static JButton kirshVerticalFilterMenuOptionButton(){
		JButton button = generateButton(new KirshFilterButtonListener(VER));
		button.setText("Vertical");
		return button;
	}
	
  public static JButton kirshG45FilterMenuOptionButton(){
    JButton button = generateButton(new KirshFilterButtonListener(G45));
    button.setText("45");
    return button;
  }
  
  public static JButton kirshG135FilterMenuOptionButton(){
    JButton button = generateButton(new KirshFilterButtonListener(G135));
    button.setText("135");
    return button;
  }
	
	public static JButton tp2p5aMaxFilterMenuOptionButton(){
		JButton button = generateButton(new TP2P5AFilterButtonListener(MAX));
		button.setText("Max");
		return button;
	}
	
	public static JButton tp2p5aAvgFilterMenuOptionButton(){
		JButton button = generateButton(new TP2P5AFilterButtonListener(AVG));
		button.setText("Average");
		return button;
	}
	
	public static JButton tp2p5aMod2FilterMenuOptionButton(){
		JButton button = generateButton(new TP2P5AFilterButtonListener(MOD));
		button.setText("Mod2");
		return button;
	}
	
	public static JButton tp2p5aMinFilterMenuOptionButton(){
		JButton button = generateButton(new TP2P5AFilterButtonListener(MIN));
		button.setText("Min");
		return button;
	}
	
	public static JButton tp2p5aHorizontalFilterMenuOptionButton(){
		JButton button = generateButton(new TP2P5AFilterButtonListener(HOR));
		button.setText("Horizontal");
		return button;
	}
	
	public static JButton tp2p5aVerticalFilterMenuOptionButton(){
		JButton button = generateButton(new TP2P5AFilterButtonListener(VER));
		button.setText("Vertical");
		return button;
	}
	
  public static JButton tp2p5aG45FilterMenuOptionButton(){
    JButton button = generateButton(new TP2P5AFilterButtonListener(G45));
    button.setText("45");
    return button;
  }
  
  public static JButton tp2p5aG135FilterMenuOptionButton(){
    JButton button = generateButton(new TP2P5AFilterButtonListener(G135));
    button.setText("135");
    return button;
  }
	
	public static JButton laplacianFilterMenuOptionButton(){
		JButton button = generateButton(new LaplacianFilterButtonListener(false));
		button.setText("Basic");
		return button;
	}
	
	public static JButton laplacianPendientFilterMenuOptionButton(JTextField thresholdField){
		JButton button = generateButton(new LaplacianFilterButtonListener(true, thresholdField));
		button.setText("Pendient control");
		return button;
	}
	
	public static JButton logFilterMenuOptionButton(JTextField maskSideField, JTextField sigmaField, JTextField thresholdField){
		return generateButton(new LoGFilterButtonListener(maskSideField,sigmaField,thresholdField));
	}
	
	/* END FILTER */
	/*Utils*/
	
	private static JButton applyButton(ActionListener listener){
		JButton button = new JButton("Apply");
		ApplyActionListener l = new ApplyActionListener(listener);
		button.addActionListener(l);
		return button;
	}
	
	private static JButton generateButton(ActionListener listener){
		JButton button = new JButton("Generate");
		button.addActionListener(listener);
		return button;
	}
}
