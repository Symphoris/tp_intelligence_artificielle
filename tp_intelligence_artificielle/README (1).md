#Object implementation of Candidate Elimination algorithm
---------------------------------------------------------
The problem is to learn a function mapping examples into two classes: positive and negative. We are given a database of examples already classified as positive or negative.
Concept learning: the process of inducing a function mapping input examples into a Boolean output. 

##Overview of the data
------------------------
This data set consists of 101 animals from a zoo. There are **16 variables** with various traits to describe the animals. The **7 Class Types** are: Mammal, Bird, Reptile, Fish, Amphibian, Bug and Invertebrate

The purpose for this data set is to be able to predict the classification of the animals, based upon the variables. 
There are 2 files:
- zoo.csv: attribute Information: (name of attribute and type of value domain)
    - animal_name: Unique for each instance
    - hair Boolean
    - feathers Boolean
    - eggs Boolean
    - milk Boolean
    - airborne Boolean
    - aquatic Boolean
    - predator Boolean
    - toothed Boolean
    - backbone Boolean
    - breathes Boolean
    - venomous Boolean
    - fins Boolean
    - legs Numeric (set of values: {0,2,4,5,6,8})
    - tail Boolean
    - domestic Boolean
    - catsize Boolean
    - class_type Numeric (integer values in range [1,7])
- class.csv: this csv describes the dataset
    - Class_Number Numeric (integer values in range [1,7])
    - Number_Of_Animal_Species_In_Class Numeric
    - Class_Type character -- The actual word description of the class
    - Animal_Names character -- The list of the animals that fall in the category of the class

These data come from [UCI Machine Learning](https://archive.ics.uci.edu/ml/datasets/Zoo)

##Candidate Elimination algorithm
-------------------------------------
It incrementally builds the version space given a hypothesis space H and a set E of examples. The examples are added one by one; each example possibly shrinks the version space by removing the hypotheses that are inconsistent with the example. The candidate elimination algorithm does this by updating the general and specific boundary for each new example. 

    def CandidateEliminationLearner(X,Y,E,H) 
        Inputs
            X: set of input features, X={X1,...,Xn} 
            Y: target feature 
            E: set of examples from which to learn 
            H: hypothesis space 
        Output
            general boundary G is included in H 
            specific boundary S is included in H consistent with E 
        Local
            G: set of hypotheses in H 
            S: set of hypotheses in H 
        Let G={true}, S={false}; 
        for each e in E do 
            if ( e is a positive example) then 
                Elements of G that classify e as negative are removed from G; 
                Each element s of S that classifies e as negative is removed and replaced by the minimal generalizations of s that classify e as positive and are less general than some member of G; 
                Non-maximal hypotheses are removed from S; 
            else
                Elements of S that classify e as positive are removed from S; 
                Each element g of G that classifies e as positive is removed and replaced by the minimal specializations of g that classifies e as negative and are more general than some member of S. 
                Non-minimal hypotheses are removed from G. 
                        
##Object implementation
---------------------------
**Approach:**
1. According to the training data there were 7 classes into which animals were classified.
2. In the training data first we considered all the instances with classification as class 1 as positive and rest as negative.
3. Now to generate the specific boundary we first considered only the positive examples and neglected the negative examples. Hence the specific boundary was generated using the Candidate Elimination Algorithm. Then we generated the general boundary considering the negative examples using the algorithm.
4. The same logic was repeated for all the classes.

**Class diagram**
- A _CSVDataReader_ class which implements a _DataReader_ interface (with a _read_ method) plays the role of Format _Adapter_.
- A _Context_ class plays the role of singleton runtime context and has 2 main attributes
    - the training date
    - the domain attribute set
- A _Concept_ class has two main attributes
    - genericBoundary is a list of _Hypothesis_
    - specificBoundary is an instance of _Hypothesis_
<br/>and 4 methods:
    - a _addTrainingInstance_ method which takes into account all the lines of training data
    - a _minimalSpecialization_ method which computes the change of the specific boundary
    - a _displayGenericBoundary_ method
    - a _displaySpecificBoundary_ method
- An _Hypothesis_ class which contains a set of attributes called attributes and 4 methods
    - _isConsistent_ returns true or false whether the current positive classification is the value from 1 to 7 for which we are finding concept hypothesis
    - _isLessGeneralThan_ computes the generalization from: *all* -> *value* -> *none*
    - _predictClass_ returns true or false depending on the attribute value of a given training data
    - _score_ returns an discrete value *ALL, VALUE, NONE*


