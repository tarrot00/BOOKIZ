import React from 'react';
import styled from 'styled-components';

function BestCard(props) {
	return (
		<BestCardContainer>
			<Crown src={props.crown} />
			<Image src={props.image} />
			<Title>
				{props.title}
			</Title>
		</BestCardContainer>
	)
}

export default BestCard;

const BestCardContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 18.75vw;
	height: 57.7778vh;
`;

const Crown = styled.img`
	width: 6.25vw;
	height: 13.3333vh;
	color: black;
`;

const Image = styled.img`
	width: 100%;
	height: 34.6667vh;
`;

const Title = styled.div`
	display: flex;
	background-color: #D9D9D9;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 9.7778vh;
	font-size: 3.3333vh;
	text-align: center;
`;