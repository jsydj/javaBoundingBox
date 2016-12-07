To run the project, simply:
1) Place sample_data.csv and acmeQueryBox.jar in same folder
2) In that folder, type "java -jar acmeQueryBox.jar"
3) Enter number four value for the bounding box (x,x,y,y)
Error input will require you enter until correct.
4) Result of cordinates found in sample_data.csv within
bounding box cordinates will be output to query_results.txt

5) For interface to boundingBox, implements BoundingBoxInterface()
and invoke:
public List<String> fetchBoundingTriples(int leftXInt, int rightXInt, int lowerYInt, int upperYInt) {
