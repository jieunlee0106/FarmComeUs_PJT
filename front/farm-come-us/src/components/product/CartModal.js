import React from "react";
import classes from "./style/CartModal.module.scss";

const CartModal = (props) => {
  const closeModal = () => {
    props.setModalOpen(false);
  };

  return(
    <div className={classes.container}>
      <button className={classes.close} onclick={closeModal}>X</button>
    </div>
  )
}

export default CartModal;