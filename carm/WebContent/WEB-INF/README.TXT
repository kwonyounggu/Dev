1. table, all_item_reviewer_mx, will allocate reviewers for each item up to the number of 'reviewers per item'
   - reviewers can be replaced with other person for the same item of the same group of the same application
   
2. table, all_review_data,
   - for the same item, reviewers are normally equal to the number of 'reviewers per item', however, when a reviewer is replaced with another person through the table reviewers, 
     then the number of reviewers in the same item will be over the 'reviewers per item',
     so when you consult to know the final agreement or disagreement, you have to get the right reviewers for an item from the table all_item_reviewer_mx.