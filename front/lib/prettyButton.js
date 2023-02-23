import React from 'react';
import css from 'styled-jsx/css';

const buttonStyles = css`
  button {
    background-color: #008CBA;
    border: none;
    color: white;
    padding: 12px 24px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    border-radius: 4px;
    cursor: pointer;
  }

  button:hover {
    background-color: #007AA3;
  }
`;

function PrettyButton(props) {
  return (
    <div>
      <button>{props.children}</button>
      <style jsx>{buttonStyles}</style>
    </div>
  );
}

export default PrettyButton;