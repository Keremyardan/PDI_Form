import React, { useState, useEffect } from 'react';
import '../components/PdiForm.css';
import logo from "../assets/cherylogo.svg"
import { LogOut } from "../components/Logout"
import SvgCar from "./SvgCar"

function PdiForm({ isReadOnly = false, form = {} }) {
    const [formData, setFormData] = useState({});
    const [hoveredPart, setHoveredPart] = useState(null);
    const [selectedParts, setSelectedParts] = useState([]);

    useEffect(() => {
        if (!form || Object.keys(form).length === 0) return;

        setFormData(prev => ({
            ...form,
            id: form.id
        }));


        const parts = [
            "solOnKapi", "sagOnKapi", "onKaput", "arkaTampon", "tavan", "onTampon",
            "arkaBagaj", "sagOnCamurluk", "solOnCamurluk", "sagArkaCamurluk",
            "solArkaCamurluk", "sagArkaKapi", "solArkaKapi"
        ];
        const selectedFromForm = parts.filter(part => form[part]);
        setSelectedParts(selectedFromForm);

        console.log("Gelen form id:", form.id);
    }, [form]);




    const handlePartHover = (partId) => {
        setHoveredPart(partId);
        console.log(`Hovered on part: ${partId}`);
    };

    const handlePartClick = (partId) => {
        setSelectedParts(prev =>
            prev.includes(partId)
                ? prev.filter(id => id !== partId)
                : [...prev, partId]
        );
    };


    const handleChange = (event) => {
        const { name, value, type, checked } = event.target;
        setFormData(prev => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    const handleSubmit = async (event) => {
        console.log("formData.id nedir:", formData.id);

        event.preventDefault();

        const credentials = localStorage.getItem("auth");
        if (!credentials) {
            alert("Lütfen önce giriş yapın.");
            return;
        }

        const isUpdate = !!formData.id;
        const endpoint = isUpdate
            ? `http://localhost:8080/api/pdi-form/${formData.id}`
            : `http://localhost:8080/api/pdi-form/submit`;

        const method = isUpdate ? "PUT" : "POST";

        const fullData = {
            id: formData.id,
            pdiYeri: formData.pdiYeri,
            model: formData.model,
            vin: formData.vin,
            kmBilgisi: formData.kmBilgisi,
            kontrolTarihi: formData.kontrolTarihi,
            fuelLitres1: formData.fuelLitres1,
            fuelLitres2: formData.fuelLitres2,
            fuelTypeBenzin1: formData.fuelTypeBenzin1,
            fuelTypeBenzin2: formData.fuelTypeBenzin2,
            gurasyon: formData.gurasyon,
            firstAid: formData.firstaid,
            additionalNotes: formData.additionalNotes,
            solOnKapi: selectedParts.includes("solOnKapi"),
            sagOnKapi: selectedParts.includes("sagOnKapi"),
            onKaput: selectedParts.includes("onKaput"),
            arkaTampon: selectedParts.includes("arkaTampon"),
            tavan: selectedParts.includes("tavan"),
            onTampon: selectedParts.includes("onTampon"),
            arkaBagaj: selectedParts.includes("arkaBagaj"),
            sagOnCamurluk: selectedParts.includes("sagOnCamurluk"),
            solOnCamurluk: selectedParts.includes("solOnCamurluk"),
            sagArkaCamurluk: selectedParts.includes("sagArkaCamurluk"),
            solArkaCamurluk: selectedParts.includes("solArkaCamurluk"),
            sagArkaKapi: selectedParts.includes("sagArkaKapi"),
            solArkaKapi: selectedParts.includes("solArkaKapi"),
            ...Object.fromEntries(
                Array.from({ length: 29 }).map((_, i) => [`functionalCheck${i}`, formData[`functionalCheck${i}`] || false])
            ),
            ...Object.fromEntries(
                Array.from({ length: 9 }).map((_, i) => [`damageDescription${i}`, formData[`damageDescription${i}`] || ""])
            )
        };

        try {
            const response = await fetch(endpoint, {
                method,
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Basic ${credentials}`
                },
                body: JSON.stringify(fullData),
            });

            if (!response.ok) {
                const errorText = await response.text();
                console.error("Sunucu Hatası:", errorText);
                throw new Error(`Form gönderimi başarısız: ${response.status}`);
            }

            alert(isUpdate ? "Form başarıyla güncellendi!" : "Form başarıyla gönderildi!");
            window.location.reload();
        } catch (error) {
            console.error("Hata", error);
            alert(`Bir hata oluştu: ${error.message}`);
        }
    };



    return (
        <div className="main-form">
            <div className="pdi-form1">

                <div className="horizontal-container">
                    <div className="left-column">
                        <div className="first-row">
                            <div className="pdiform-cell">
                                PDI Formu
                            </div>
                        </div>
                        <div className="first-row2">
                            <div className="pdiregistery-cell">
                                PDI Muayene Kayıt Tablosu
                            </div>
                        </div>
                    </div>
                    <div className="first-row3">
                        <div className="pdiimage-cell">

                            <button onClick={LogOut} className='logout'>Çıkış Yap</button>
                            <img className="image" src={logo} alt="logo" />


                        </div>
                    </div>
                </div>
                <div className="second-row">
                    <div className="second-cell">
                        <span className="cell-text">PDI Yeri: </span>
                        <select
                            className="kmbox1"
                            name="pdiYeri"
                            value={formData.pdiYeri || ''}
                            onChange={handleChange}
                            disabled={isReadOnly}
                        >
                            <option value="">Seçiniz</option>
                            <option value="CHERY">CHERY</option>

                        </select>
                    </div>

                    <div className="second-cell">
                        <span className="cell-text">Model: </span>
                        <select
                            className="kmbox1"
                            name="model"
                            value={formData.model || ''}
                            onChange={handleChange}
                            disabled={isReadOnly}
                        >
                            <option value="">Seçiniz</option>
                            <option value="Omoda 5">Omoda 5</option>
                            <option value="Omoda 5 Pro">Omoda 5 Pro</option>
                            <option value="Tiggo 3">Tiggo 3</option>
                            <option value="Tiggo 7 Pro">Tiggo 7 Pro</option>
                            <option value="Tiggo 7 Pro Max">Tiggo 7 Pro Max</option>
                            <option value="Tiggo 8">Tiggo 8</option>
                            <option value="Tiggo 8 Pro Max">Tiggo 8 Pro Max</option>
                        </select>
                    </div>


                    <div className="second-cell">
                        <span className="cell-text">Vin: </span>
                        <textarea
                            className='kmbox'
                            name="vin"
                            value={formData.vin || ''}
                            onChange={handleChange}
                            disabled={isReadOnly}
                        />
                    </div>

                    <div className="second-cell">
                        <span className="cell-text">KM Bilgisi: </span>
                        <textarea
                            className='kmbox'
                            name="kmBilgisi"
                            value={formData.kmBilgisi || ''}
                            onChange={handleChange}
                            disabled={isReadOnly}
                        />
                    </div>

                    <div className="second-cell"><span className="cell-text">Kontrol Tarihi: </span><input
                        type="date"
                        className='dateinput'
                        name="kontrolTarihi"
                        value={formData.kontrolTarihi || ""}
                        onChange={handleChange}
                        disabled={isReadOnly}
                    />
                    </div>
                </div>
                <div className="third-row">
                    <div className="third-cell">Tip</div>
                    <div className="fourth-cell">A-Çizik &nbsp;&nbsp; B-Eksik Boya &nbsp;&nbsp; C-Renk Farkı &nbsp;&nbsp; D-Sürtünme &nbsp;&nbsp; E-Deformasyon &nbsp;&nbsp; F-Çökük &nbsp;&nbsp; G-Kırık &nbsp;&nbsp; H-Ayrılma &nbsp;&nbsp; I-Pas &nbsp;&nbsp; J-Parazit &nbsp;&nbsp; H-Gaz/Gaz Sızıntısı Yok</div>
                </div>
                <div className="fourth-row">
                    <div className="fifth-cell">HASAR TANIMI</div>
                    <div className="sixth-cell">
                        <SvgCar
                            onPartHover={isReadOnly ? undefined : handlePartHover}
                            onPartClick={isReadOnly ? undefined : handlePartClick}
                            selectedParts={selectedParts}
                            hoveredPart={hoveredPart}
                        />


                    </div>

                    <div className="lines">
                        <div >

                            <div className="seventh-cell">{"1-)"}</div>
                            <div className="seventh-cell">{"2-)"}</div>
                            <div className="seventh-cell">{"3-)"}</div>
                            <div className="seventh-cell">{"4-)"}</div>
                            <div className="seventh-cell">{"5-)"}</div>
                            <div className="seventh-cell">{"6-)"}</div>
                            <div className="seventh-cell">{"7-)"}</div>
                            <div className="seventh-cell">{"8-)"}</div>
                            <div className="seventh-cell">{"9-)"}</div>
                        </div>
                        <div >
                            {[...Array(9)].map((_, index) => (
                                <div className="eighth-cell" key={index}>
                                    <textarea
                                        className="eighth-cell-textarea"
                                        name={`damageDescription${index}`}
                                        value={formData[`damageDescription${index}`] || ''}
                                        onChange={handleChange}
                                        disabled={isReadOnly}
                                    />
                                </div>
                            ))}

                        </div>
                    </div>
                </div>
            </div>


            <div className="pdi-form2">
                <div className='form2-container'>
                    <div className='form2-first-row'>
                        <div className="form2-first-cell">&nbsp;&nbsp;Fonksiyonel Kontrol Teknisyeni: </div>
                        <div className="form2-first-cell">&nbsp;&nbsp;Estetik Kontrol Teknisyeni: </div>
                        <div className="form2-first-cell">&nbsp;&nbsp;Test Şöförü:</div>
                    </div>
                    <div className='form2-second-row-container'>
                        <div className='form2-second-row'>
                            {["Akü (Minimum 11;00 olacak)", "Motor Dairesi Genel Kontrol", "Fren Hidroliği", "Motor Yağı",
                                "Aktarma Organları", "Cam Suyu", "Gösterge Paneli", "Radyo ve Navigasyon",
                                "Cam ve Sunroof Kontrolü", "Klima Kontrolü", "Anahtar ve Merkezi Kiit Kontrolü",
                                "İç Aydınlatma Kontrolü", "İç Döşeme ve Kemer Kontrolü", "Stepne",
                                "Kriko Takımı", "Reflektör", "Bagaj Perdesi (Tiggo 7 ve 8 kişilik koltuklu modelde yok)"]
                                .map((labelText, index) => (
                                    <label key={index}>
                                        <input
                                            type="checkbox"
                                            name={`functionalCheck${index}`}
                                            checked={formData[`functionalCheck${index}`] || false}
                                            onChange={isReadOnly ? () => { } : handleChange}
                                        />
                                        {labelText}
                                    </label>
                                ))}
                        </div>
                        <div className='form2-box-container'>
                            <div className='form2-third-row'>
                                {[
                                    "Anten",
                                    "Paspas",
                                    "Yangın Tüpü",
                                    "Trafik Seti",
                                    "Kitapçıklar",
                                    "Dış Gövde Durumu",
                                    "Logo Durumu",
                                    "Yol Yardım Etiketi"
                                ].map((labelText, index) => (
                                    <label key={index}>
                                        <input
                                            type="checkbox"
                                            name={`functionalCheck${index + 16}`}
                                            checked={formData[`functionalCheck${index + 16}`] || false}
                                            onChange={handleChange}
                                        />
                                        {labelText}
                                    </label>
                                ))}
                            </div>

                            <div className='form2-fifth-row'>
                                <text className='form2-fifth-row-text'>
                                    &nbsp;&nbsp;Lastik Kontrol Teknisyeni:
                                </text>
                                {[
                                    "Lastik Hava Basınçlarının Kontrolü",
                                    "Lastik Markası"
                                ].map((labelText, index) => (
                                    <label key={index}>
                                        <input
                                            type="checkbox"
                                            name={`functionalCheck${index + 24}`}
                                            checked={formData[`functionalCheck${index + 24}`] || false}
                                            onChange={handleChange}
                                        />
                                        {labelText}
                                    </label>
                                ))}
                            </div>
                        </div>
                        <div className='form2-fourth-row-container'>
                            <div className='form2-fourth-row'>
                                <div className='form2-fourth-row-top-text'>
                                    {[
                                        "Far ve Stop Kontrolleri",
                                        "Klima Kontrolü",
                                        "Motor Arıza Gösterge Kontrolü"
                                    ].map((labelText, index) => (
                                        <label key={index}>
                                            <input
                                                type="checkbox"
                                                name={`functionalCheck${index + 28}`}
                                                checked={formData[`functionalCheck${index + 28}`] || false}
                                                onChange={handleChange}
                                            />
                                            {labelText}
                                        </label>
                                    ))}
                                </div>
                                <div className="fuel-section">
                                    <div className="fuel-type">
                                        <span className='fuel-type-headers'>YAKIT TÜRÜ:</span>
                                        <label>
                                            <input
                                                type="checkbox"
                                                name="fuelTypeBenzin1"
                                                checked={formData.fuelTypeBenzin1 || false}
                                                onChange={handleChange}
                                            />
                                            Benzin
                                        </label>
                                        <label>
                                            <input
                                                type="checkbox"
                                                name="fuelTypeBenzin2"
                                                checked={formData.fuelTypeBenzin2 || false}
                                                onChange={handleChange}
                                            />
                                            Benzin
                                        </label>
                                    </div>
                                    <div className="fuel-litre">
                                        <span className='fuel-type-headers'>LİTRE:</span>
                                        <input
                                        className='fuel-box'
                                            type="text"
                                            name="fuelLitres1"
                                            value={formData.fuelLitres1 || ''}
                                            onChange={handleChange}
                                            placeholder="Litre Miktarı"
                                            disabled={isReadOnly}
                                        />

                                        <input
                                        className='fuel-box'
                                            type="text"
                                            name="fuelLitres2"
                                            value={formData.fuelLitres2 || ''}
                                            onChange={handleChange}
                                            placeholder="Litre Miktarı"
                                        />
                                    </div>

                                </div>
                            </div>
                            <div className='form2-sixth-row'>
                                <text className='form2-sixth-row-text'>
                                    &nbsp;&nbsp;İlave Notlar:
                                </text>
                                <textarea className='additionalNotes'
                                    name="additionalNotes"
                                    value={formData.additionalNotes || ''}
                                    onChange={handleChange}
                                    placeholder="Ek notlarınızı buraya yazabilirsiniz..."


                                />
                            </div>

                        </div>

                    </div>
                    <div className='form2-last-row'>
                        <div className="form2-last-row-checkbox">
                            <span className='fuel-type-headers'>*gurasyon</span>
                            <label>
                                <input
                                    type="checkbox"
                                    name="gurasyon"
                                    checked={formData.gurasyon || false}
                                    onChange={handleChange}
                                />

                            </label>
                            <span className='fuel-type-headers'>İlk Yardım Çantası</span>
                            <label>
                                <input
                                    type="checkbox"
                                    name="firstaid"
                                    checked={formData.firstaid || false}
                                    onChange={handleChange}
                                />

                            </label>
                            <label>
                                <text className='explanation'>Nihai yorumlama hakkı Chery International'a aittir.</text>
                            </label>

                        </div>

                    </div>
                    <div className='buttons'>
                        <button
                            type="submit"
                            className="submit-button"
                            onClick={handleSubmit}
                        >
                            {formData.id ? "Güncelle" : "Gönder"}
                        </button>

                        <button
                            type="button"
                            className="clear-button"
                            onClick={() => {
                                if (window.confirm("Formu sıfırlamak istediğinizden emin misiniz?")) {
                                    setFormData({});
                                    setSelectedParts([]);
                                    setFunctionalChecks({});
                                }
                            }}
                        >
                            Formu Temizle
                        </button>
                    </div>

                </div>

            </div>

        </div>
    );
}

export default PdiForm;