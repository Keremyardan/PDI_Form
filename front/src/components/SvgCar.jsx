import React, { useState } from 'react';

const SvgCar = ({ onPartHover, onPartClick }) => {
    const [hoveredPart, setHoveredPart] = useState([]);
    const [clickedPart, setClickedPart] = useState(null);

    const handleMouseEnter = (partId) => {
        setHoveredPart(partId);
        if (onPartHover) {
            onPartHover(partId);
        }
    };

    const handleMouseLeave = () => {
        setHoveredPart(null);
        if (onPartHover) {
            onPartHover(null);
        }
    };

    const handleClick = (partId) => {
        setClickedPart(partId === clickedPart ? null : partId);
        if(onPartClick) {
            onPartClick(partId);
        }
    }

const getPartStyle = (partId) => {
        if (clickedPart === partId || hoveredPart === partId) {
            return { fill: '#F44336', cursor: 'pointer', transition: 'fill 0.2s ease' };
        }
        return { cursor: 'pointer', transition: 'fill 0.2s ease' };
    };
    return (
        <svg version="1.1" id="katman_2" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
            x="0px" y="0px" viewBox="0 0 338.2 411.9" style={{ enableBackground: 'new 0 0 338.2 411.9' }} xmlSpace="preserve"
            className="car-svg"
        >
            <style type="text/css">
                {`
                    .st0{fill:#C6C6C6;}
                    .st1{fill:#FFFFFF;}
                    .st2{fill:#9D9D9C;}
                    .car-svg path,
                    .car-svg circle,
                    .car-svg polygon,
                    .car-svg rect {
                        transition: fill 0.2s ease;
                        cursor: pointer;
                    }
                    .car-svg path:hover,
                    .car-svg circle:hover,
                    .car-svg polygon:hover,
                    .car-svg rect:hover {
                        fill: #F44336;
                    }
                `}
            </style>
            {/* Sol Taraf */}
            <g id="sol">
                <path class="st0" d="M28.7,318.4v28.5c0,0,5-0.3,7.8,6.8h14.7l3.8-3.8h23.4c0,0,36.7-42.6,36.7-76v-56c0,0-0.3-27.3-32.9-84.6
	L65.3,65.9l-12.8-3.1v-3.9H33.1V62h-4.4v28.2h4.4c13.3,0,24.2,10.8,24.2,24.2s-10.8,24.2-24.2,24.2l-4.4,0v131.7h4.4
	c13.3,0,24.2,10.8,24.2,24.2c0,13.3-10.8,24.2-24.2,24.2L28.7,318.4z" style={{ pointerEvents: 'none' }} />
                <path
                    id="solArkaCamurluk"
                    className="st2"
                    d="M60.9,304.6v39h-6l-6.1,6.1h-9.6c0-4.6-6.1-5.9-6.1-5.9v-21.7c11.7,0,21.7-7.3,25.8-17.5L60.9,304.6z"
                    style={getPartStyle('solArkaCamurluk')}
                    onMouseEnter={() => handleMouseEnter('solArkaCamurluk')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('solArkaCamurluk')}
                />
                <path
                    id="solArkaKapi"
                    className="st2"
                    d="M33.1,209.6l78.3,15.9v48.5l-50.5,20.4c0-15.3-12.4-27.8-27.8-27.8V209.6z"
                    style={getPartStyle('solArkaKapi')}
                    onMouseEnter={() => handleMouseEnter('solArkaKapi')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('solArkaKapi')}
                />
                <path
                    id="solOnKapi"
                    className="st2"
                    d="M63.3,119.7c51.3,60,48,103.8,48,103.8l-78.3-16.3v-65.1c13.5,0,24.8-9.7,27.3-22.5L63.3,119.7z"
                    style={getPartStyle('solOnKapi')}
                    onMouseEnter={() => handleMouseEnter('solOnKapi')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('solOnKapi')}
                />
                <path
                    id="solOnCamurluk"
                    className="st2"
                    d="M59.3,68.8V105c-3.8-10.7-14.1-18.4-26.2-18.4V66.3h2.3v-2.9h14.1v3.3L59.3,68.8z"
                    style={getPartStyle('solOnCamurluk')}
                    onMouseEnter={() => handleMouseEnter('solOnCamurluk')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('solOnCamurluk')}
                />
                <path id="sagKomple" class="st0"
                    d="M306.1,318.4v28.5c0,0-5-0.3-7.8,6.8h-14.7l-3.8-3.8h-23.4c0,0-36.7-42.6-36.7-76v-56
                c0,0,0.3-27.3,32.9-84.6l16.7-67.5l12.8-3.1v-3.9h19.4V62h4.4v28.2h-4.4c-13.3,0-24.2,10.8-24.2,24.2s10.8,24.2,24.2,24.2l4.4,0
                v131.7h-4.4c-13.3,0-24.2,10.8-24.2,24.2c0,13.3,10.8,24.2,24.2,24.2L306.1,318.4z" style={{ pointerEvents: 'none' }} />
                <path class="st0"
                    d="M204.4,61.4c18.7,5,18.1,12.3,19,20.9s3.9,63.3,3.9,63.3S215,191.2,210.9,218v56c0,0-0.3,21.6,10.6,45
                s-0.3,28.7-7.5,30.7s-36.3,5.3-45.6,5.3h-1c-9.3,0-38.4-3.3-45.6-5.3c-7.3-2-18.4-7.3-7.5-30.7s10.6-45,10.6-45v-56
                c-4.2-26.8-16.5-72.3-16.5-72.3s3.1-54.7,3.9-63.3c0.8-8.7,0.3-15.9,19-20.9C131.2,61.4,161.7,52.5,204.4,61.4z" style={{ pointerEvents: 'none' }} />

                <circle
                    id="solOnTeker"
                    className="st0"
                    cx="33.1"
                    cy="114.3"
                    r="20.4"

                    style={{ pointerEvents: 'none' }}
                />
                <circle
                    id="solArkaTeker"
                    className="st0"
                    cx="33.1"
                    cy="294.3"
                    r="20.4"

                    style={{ pointerEvents: 'none' }}
                />
                <polygon className="st1" points="75.6,291.5 75.6,308.5 108.3,278.1 " onMouseEnter={() => onPartHover('solArkaKelebek')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <path className="st1" d="M75.6,141.8c0,0,31.3,44.1,32.7,76.2l-32.7-7.3V141.8z" onMouseEnter={() => handleMouseEnter('solOnCam')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
            </g>

            {/* Sağ Taraf */}
            <g id="sag">
                <path
                    id="sagArkaCamurluk"
                    className="st2"
                    d="M275.8,304.6c4.1,10.3,14.1,17.5,25.8,17.5v21.7c0,0-6.1,1.3-6.1,5.9h-9.6l-6.1-6.1h-6v-39L275.8,304.6z"
                    style={getPartStyle('sagArkaCamurluk')}
                    onMouseEnter={() => handleMouseEnter('sagArkaCamurluk')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('sagArkaCamurluk')}
                />
                <path
                    id="sagArkaKapi"
                    className="st2"
                    d="M301.6,266.5c-15.3,0-27.8,12.4-27.8,27.8l-50.5-20.4v-48.5l78.3-15.9V266.5z"
                    style={getPartStyle('sagArkaKapi')}
                    onMouseEnter={() => handleMouseEnter('sagArkaKapi')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('sagArkaKapi')}
                />
                <path
                    id="sagOnKapi"
                    className="st2"
                    d="M274.3,119.6c2.5,12.8,13.7,22.5,27.3,22.5v65.1l-78.3,16.3c0,0-3.3-43.8,48-103.8L274.3,119.6z"
                    style={getPartStyle('sagOnKapi')}
                    onMouseEnter={() => handleMouseEnter('sagOnKapi')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('sagOnKapi')}
                />
                <path
                    id="sagOnCamurluk"
                    className="st2"
                    d="M301.6,86.5c-12.1,0-22.3,7.7-26.2,18.4V68.8l9.7-2.1v-3.3h14.1v2.9h2.3V86.5z"
                    style={getPartStyle('sagOnCamurluk')}
                    onMouseEnter={() => handleMouseEnter('sagOnCamurluk')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('sagOnCamurluk')}
                />
                <circle
                    id="sagOnTeker"
                    className="st0"
                    cx="301.6"
                    cy="114.3"
                    r="20.4"
                    style={{ pointerEvents: 'none' }}
                />
                <circle
                    id="sagArkaTeker"
                    className="st0"
                    cx="301.6"
                    cy="294.3"
                    r="20.4"
                    style={{ pointerEvents: 'none' }}
                />
                <polygon id="sagArkaKelebek" className="st1" points="259.1,291.5 259.1,308.5 226.4,278.1 " onMouseEnter={() => handleMouseEnter('sagArkaKelebek')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <path id="sagOnCam" className="st1" d="M259.1,141.8c0,0-31.3,44.1-32.7,76.2l32.7-7.3V141.8z" style={{ pointerEvents: 'none' }} />
                <polygon id="sagArkaCam" class="st1" points="259.1,222.7 259.1,282.7 226.4,270.1 226.4,229.7" onMouseEnter={() => handleMouseEnter('sagArkaCam')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <polygon id="solArkaCam" className="st1" points="75.6,222.7 75.6,282.7 108.3,270.1 108.3,229.7 " onMouseEnter={() => handleMouseEnter('solArkaCam')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />

            </g>

            {/* Ön ve Arka */}
            <g id="onArka">
                <path
                    id="onTampon"
                    className="st2"
                    d="M216.1,50.9h-95.7c-2.2,0-3.9-1.8-3.9-3.9V32.8c0-2.2,1.8-3.9,3.9-3.9h95.7c2.2,0,3.9,1.8,3.9,3.9v14.2C220.1,49.1,218.3,50.9,216.1,50.9z"
                    style={getPartStyle('onTampon')}
                    onMouseEnter={() => handleMouseEnter('onTampon')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('onTampon')}
                />
                <path
                    id="arkaTampon"
                    className="st2"
                    d="M216.1,385.9h-95.7c-2.2,0-3.9-1.8-3.9-3.9v-14.2c0-2.2,1.8-3.9,3.9-3.9h95.7c2.2,0,3.9,1.8,3.9,3.9V382C220.1,384.1,218.3,385.9,216.1,385.9z"
                    style={getPartStyle('arkaTampon')}
                    onMouseEnter={() => handleMouseEnter('arkaTampon')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('arkaTampon')}
                />
            </g>

            {/* Orta Kısımlar */}
            <g id="orta">
                <path className="st1" d="M131.4,277.1c0,0,2.4,16.4-9.5,39.3c0,0,12.8,7.3,45.6,7.3s45.1-7.5,45.1-7.5s-8-16.2-8.9-39.1H131.4z" onMouseEnter={() => handleMouseEnter('ustKisim')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <path className="st1" d="M115,144.6l16.5,69.2h72.3l16.1-69.2C219.8,144.6,174,122.2,115,144.6z" onMouseEnter={() => handleMouseEnter('ortaKisim')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
            </g>

            {/* Kaput ve Bagaj */}
            <g id="kaputBagaj">
                <path
                    id="onKaput"
                    className="st2"
                    d="M118.1,76.5c0,0,2.6-15.6,49.2-15.6s50.6,16,50.6,16l3.7,62.9c0,0-23.8-10-53.8-10s-53.4,9-53.4,9L118.1,76.5z"
                    style={getPartStyle('onKaput')}
                    onMouseEnter={() => handleMouseEnter('onKaput')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('onKaput')}
                />
                <path
                    id="arkaBagaj"
                    className="st2"
                    d="M167.8,350.8c-7.2,0-48.9-2-51.7-9.5s2.5-20.1,2.5-20.1s18.8,9.1,48.9,9.2h0.1c30.2-0.1,48.9-9.2,48.9-9.2s5.3,12.6,2.5,20.1C216.2,348.6,176.4,350.7,167.8,350.8L167.8,350.8z"
                    style={getPartStyle('arkaBagaj')}
                    onMouseEnter={() => handleMouseEnter('arkaBagaj')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('arkaBagaj')}
                />
            </g>

            {/* Tavan */}
            <g id="tavan">
                <path
                    id="tavan"
                    className="st2"
                    d="M203.7,219.6c-2.5,24,0,52.5,0,52.5h-72.3c2.8-25.4,0-52.5,0-52.5H203.7z"
                    style={getPartStyle('tavan')}
                    onMouseEnter={() => handleMouseEnter('tavan')}
                    onMouseLeave={handleMouseLeave}
                    onClick={() => handleClick('tavan')}   
                />
            </g>

            {/* Köşeler */}
            <g id="kose">
                <path className="st0" d="M123.4,394.4c0,3.1,2.5,5.5,5.5,5.5h11.7c3.1,0,5.5-2.5,5.5-5.5V390h-22.8V394.4z" onMouseEnter={() => handleMouseEnter('altSolKose')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <path className="st0" d="M190.4,394.4c0,3.1,2.5,5.5,5.5,5.5h11.7c3.1,0,5.5-2.5,5.5-5.5V390h-22.8V394.4z" onMouseEnter={() => handleMouseEnter('altSagKose')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <path className="st0" d="M213.3,19.9c0-3.1-2.5-5.5-5.5-5.5H196c-3.1,0-5.5,2.5-5.5,5.5v4.4h22.8V19.9z" onMouseEnter={() => handleMouseEnter('ustSagKose')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <path className="st0" d="M146.3,19.9c0-3.1-2.5-5.5-5.5-5.5H129c-3.1,0-5.5,2.5-5.5,5.5v4.4h22.8V19.9z" onMouseEnter={() => handleMouseEnter('ustSolKose')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
            </g>

            {/* Plakalar */}
            <g id="plaka">
                <rect x="119.1" y="370" className="st1" width="22" height="10.6" onMouseEnter={() => handleMouseEnter('plakaSol')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <rect x="195.6" y="370" className="st1" width="22" height="10.6" onMouseEnter={() => handleMouseEnter('plakaSag')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
            </g>

            {/* Farlar */}
            <g id="farlar">
                <polygon className="st1" points="185.6,35 217.6,35 217.6,45.6 197.6,43.6 " onMouseEnter={() => handleMouseEnter('solFar')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
                <polygon className="st1" points="119.1,35 151,35 140,43.6 119.1,45.6 " onMouseEnter={() => handleMouseEnter('sagFar')} onMouseLeave={handleMouseLeave} style={{ pointerEvents: 'none' }} />
            </g>
        </svg>
    );
};

export default SvgCar;
