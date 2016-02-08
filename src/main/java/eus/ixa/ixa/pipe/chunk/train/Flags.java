/*
 * Copyright 2014 Rodrigo Agerri

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package eus.ixa.ixa.pipe.chunk.train;

import opennlp.tools.util.TrainingParameters;

public class Flags {
  /**
   * Default beam size for decoding.
   */
  public static final int DEFAULT_BEAM_SIZE = 3;
  public static final int DEFAULT_FOLDS_VALUE = 10;
  public static final String DEFAULT_EVALUATE_MODEL = "off";
  public static final String DEFAULT_FEATURESET_FLAG = "Baseline";
  public static final String DEFAULT_DICT_PATH = "off";
  public static final int DEFAULT_DICT_CUTOFF = -1;
  public static final String DEFAULT_OUTPUT_FORMAT = "naf";
  public static final String DEFAULT_HOSTNAME = "localhost";

  private Flags() {
  }

  public static String getLanguage(final TrainingParameters params) {
    String lang = null;
    if (params.getSettings().get("Language") == null) {
      langException();
    } else {
      lang = params.getSettings().get("Language");
    }
    return lang;
  }

  public static String getDataSet(final String dataset,
      final TrainingParameters params) {
    String trainSet = null;
    if (params.getSettings().get(dataset) == null) {
      datasetException();
    } else {
      trainSet = params.getSettings().get(dataset);
    }
    return trainSet;
  }

  public static String getModel(final TrainingParameters params) {
    String model = null;
    if (params.getSettings().get("OutputModel") == null) {
      modelException();
    } else if (params.getSettings().get("OutputModel") != null
        && params.getSettings().get("OutputModel").length() == 0) {
      modelException();
    } else {
      model = params.getSettings().get("OutputModel");
    }
    return model;
  }

  public static Integer getBeamsize(final TrainingParameters params) {
    Integer beamsize = null;
    if (params.getSettings().get("BeamSize") == null) {
      beamsize = Flags.DEFAULT_BEAM_SIZE;
    } else {
      beamsize = Integer.parseInt(params.getSettings().get("BeamSize"));
    }
    return beamsize;
  }

  public static String getFeatureSet(final TrainingParameters params) {
    String featureSet = null;
    if (params.getSettings().get("FeatureSet") != null) {
      featureSet = params.getSettings().get("FeatureSet");
    } else {
      featureSet = Flags.DEFAULT_FEATURESET_FLAG;
    }
    return featureSet;
  }

  public static Integer getFolds(final TrainingParameters params) {
    Integer folds = null;
    if (params.getSettings().get("Folds") == null) {
      folds = Flags.DEFAULT_FOLDS_VALUE;
    } else {
      folds = Integer.parseInt(params.getSettings().get("Folds"));
    }
    return folds;
  }

  public static void modelException() {
    System.err
        .println("Please provide a model in the OutputModel field in the parameters file!");
    System.exit(1);
  }

  public static void langException() {
    System.err
        .println("Please fill in the Language field in the parameters file!");
    System.exit(1);
  }

  public static void datasetException() {
    System.err
        .println("Please specify your training/testing sets in the TrainSet and TestSet fields in the parameters file!");
    System.exit(1);
  }

  public static void corpusFormatException() {
    System.err
        .println("Please fill in CorpusFormat field in the parameters file!");
    System.exit(1);
  }

}
