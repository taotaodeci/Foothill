# swapFirstAndLast takes a Python list as parameter and modifies the list parameter by swapping its first and last elements.
# This method does not return anything or do any input/output.
def swapFirstAndLast(myList): 
    if len(myList) > 1:
        tmp = myList[0]
        myList[0] = myList[-1]
        myList[-1] = tmp

# shiftRight takes a Python list as parameter and modifies the list parameter by shifting all elements in the list to the right and putting the last (rightmost) element in the zeroth position of the list.
# This method does not return anything or do any input/output.
def shiftRight(myList):
    tmp = myList[-1]
    for i in reversed(range(len(myList)-1)):
        myList[i+1] = myList[i]
    myList[0] = tmp

# double takes a Python list as parameter and modifies the list parameter by doubling the value of each element in the list.
# This method does not return anything or do any input/output.
def double(myList):
    for i in range(len(myList)):
        myList[i] = 2 * myList[i]

# isSorted Takes a Python list as parameter and returns True if the list parameter is sorted in ascending order, returns False otherwise.
# This method does not do any input/output.
def isSorted(myList):
    return all(myList[i] <= myList[i+1] for i in range(len(myList)-1))
    
# replaceEvens Takes a Python list as parameter and replaces any even elements of the list parameter with a zero.
# This method does not return anything or do any input/output.
def replaceEvens(myList):
    for i in range(len(myList)):
        if (myList[i] % 2 == 0):
            myList[i] = 0
    
# --------- main ----------

# tests swapFirstAndLast()
print ('RUN for: swapFirstAndLast(myList):')
myList = [1,4,2,9]
print (myList)
swapFirstAndLast(myList)
print (myList)

# tests shiftRight()
print ()
print ('RUN for: shiftRight(myList):')
myList = [1,4,2,9]
print (myList)
shiftRight(myList)
print (myList)

# tests double()
print ()
print ('RUN for: double(myList):')
myList = [1,4,2,9]
print (myList)
double(myList)
print (myList)

# tests isSorted()
print ()
print ('RUN for: isSorted(myList):')
myList = [1,4,2,9]
if isSorted(myList):
    print ("myList is sorted")
else:
    print ("myList is not sorted")
print ()
print ('RUN for: isSorted(myOtherList):')
myOtherList = [1,2,4,9]
if isSorted(myOtherList):
    print ("myOtherList is sorted")
else:
    print ("myOtherList is not sorted")

# tests replaceEvens()
print ()
print ('RUN for: replaceEvens')
myList = [1,4,2,9]
print (myList)
replaceEvens(myList)
print (myList)
