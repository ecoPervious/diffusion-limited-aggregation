# diffusion-limited-aggregation
## Demo code for multiple usages of diffusion limited aggregation

This currently has a default implementation of the diffusion limited aggregation algorithm.

There is currently only a point attractor model generator implementing the model generator interface.

There are currently no views for the generated models but there is a toString implemented on the model to allow the generated particles to be checked visually.

The model/business logic is largely unit tested with good coverage.

TODO
Implement optimised version of point attractor using bounding box of current stuck particles

Implement line attractor

Implement image output view

Implement live output view that shows the aggregation building up over time in a 2D canvas

Implement different colouring options using the stuck order

Possibly implement a terrain view which transforms the aggregation model into a simple terrain based on stuck order?
