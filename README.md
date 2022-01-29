# Segment_Tree

A segment tree is a data structure that is used to perform range queries and range updates. It is a height-balanced binary tree. It can solve range queries in O(log n) time. Each intermediate node of the tree represents a segment of the data set. They are useful when working with ranges of numerical data. In this project, the following use cases have been implemented.

i) Min and max query from a given range

ii) Lazy propagation in segment tree

iii) Persistent Segment tree

## Min and Max Query from a given range

### Range Minimum Query
The leaf nodes will be the elements of the input array while the internal node represents the minimum of all leaves under it. If the range of node is within the start query index and end query index, then the value in the node is returned. Else, if the range of node is completely outside started query index and end query index, then Infinite is returned, else, the method is called with respect to the right and left child. (Range with respect to array index).

**Input:** A array of elements to form a segment tree and the range in which the minimum query should be calculated

**Output:** Minimum of values in the given range is printed.

### Range Maximum query
The leaf nodes will be the elements of the input array while the internal node represents the maximum of all leaves under it. If the range of node is within the start query index and end query index, then the value in the node is returned. Else, if the range of node is completely outside started query index and end query index, then -1 is returned,  else, the method is called with respect to the right and left child. (Range with respect to array index).

**Input:** A array of elements to form a segment tree and the range in which the minimum query should be calculated

**Output:** Maximum of values in the given range is printed.

## Lazy Propagation in segment tree

1) Start with the root of the segment tree. 
2) If the array index to be updated is not in the current node’s range, then return 
3) Else update current node and recur for children.

**Input:** A array of elements to form a segment tree and the range in which the sum of the values has to be calculated and the range in which the value is to be updated along with the value to be updated.
**Output:** Original sum and the updated sum of values in a given range

## Persistent Segment tree

For a given segment tree, if a node is updated, then all the affected nodes are taken and a new version is created with only these nodes. Values are then assigned to the new nodes. Similarly, all the versions of the segment tree are created. To keep track of the versions, keep track of their root node. Then for each range sum query, we will pass the required version’s root node in our query function and output the required sum.

**Input:** An array and different point update operations.

**Output:** The sum of elements in range r1 to r2 just after the nth update.

## Outputs

### User choice
<br>
<p align="center">
   <img align="center" height="300" src="https://github.com/devikamanoj/Segment-Tree/blob/main/OUTPUT/fig1.png"/>
 </p>
  
### Exploring the Options
<table>
     <tr>
          <td><img height="200" src="https://github.com/devikamanoj/Segment-Tree/blob/main/OUTPUT/fig2.png" /><br /><center><b>Minimum Range Query</b></center></td>
          <td><img height="200" src="https://github.com/devikamanoj/Segment-Tree/blob/main/OUTPUT/fig4.png" /><br /><center><b>Maximum Range Query</b></center></td>
     </tr>
     <tr>
          <td><img height="200" src="https://github.com/devikamanoj/Segment-Tree/blob/main/OUTPUT/fig6.png" /><br /><center><b>Persistent Segment Tree - Version 1</b></center></td>
          <td><img height="200" src="https://github.com/devikamanoj/Segment-Tree/blob/main/OUTPUT/fig7.png" /><br /><center><b>Persistent Segment Tree - Version 1</b></center></td>
     </tr>
    <tr>
            <td><img height="200" src="https://github.com/devikamanoj/Segment-Tree/blob/main/OUTPUT/fig8.png" /><br /><center><b>Sum of Given Range</b></center></td>
            <td><img height="200" src="https://github.com/devikamanoj/Segment-Tree/blob/main/OUTPUT/fig9.png" /><br /><center><b>Lazy Propagation</b></center></td>
       </tr>

</table>

## Contributions

**Nivedita Rajesh:** Implementation of Persistent segment tree and Min & max query from a given range

**M Devika:** Implementation of Lazy Propagation (update segment tree and sum in the given range)

