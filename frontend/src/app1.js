import React from "react";
import ReactDOM from "react-dom";
import {
  default as FilterableProductTable,
  PRODUCTS,
} from "./FilterableProductTable.js";

ReactDOM.render(
  <FilterableProductTable products={PRODUCTS} />,
  document.getElementById("root")
);
