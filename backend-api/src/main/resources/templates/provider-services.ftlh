<#import "layout.ftlh" as layout>
<@layout.page title="Manage Services">
    <style>
        .svc { background-color: #fff8f6; border-radius: 8px; padding: 15px; margin-bottom: 12px; }
        .svc-top { display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px; }
        .svc-info { flex: 1; }
        .svc-info h3 { color: #5a4145; margin-bottom: 4px; }
        .svc-info p { color: #666; font-size: 14px; margin-bottom: 2px; }
        .svc-price { font-weight: bold; color: #5a4145; }
        .svc-btn { border: none; padding: 8px 14px; border-radius: 6px; cursor: pointer; font-size: 14px; background-color: #eee; color: #666; }
        .svc-btn:hover { background-color: #ddd; }
        .svc details { margin-top: 12px; }
        .svc summary { cursor: pointer; color: #b76e79; font-weight: bold; margin-bottom: 12px; }
        .svc-row { display: flex; gap: 15px; }
        .svc-row .form-group { flex: 1; }
    </style>
    <main class="container">
        <h1>Manage My Services</h1>
        <p class="subtitle">Add services and update pricing. <a href="/provider/profile">Back to my profile</a></p>
        <#if successMessage??>
            <p class="message">${successMessage}</p>
        </#if>
        <#if errorMessage??>
            <p class="message" style="color: #b00020;">${errorMessage}</p>
        </#if>

        <section class="card">
            <h2>Current Services</h2>
            <#if services?? && services?has_content>
                <#list services as svc>
                    <div class="svc">
                        <div class="svc-top">
                            <div class="svc-info">
                                <h3>${svc.name()}</h3>
                                <#if svc.description()?has_content>
                                    <p>${svc.description()}</p>
                                </#if>
                                <#if svc.durationMinutes()??>
                                    <p>${svc.durationMinutes()?c} minutes</p>
                                </#if>
                            </div>
                            <div class="svc-price">
                                <#if svc.price()??>$${svc.price()?string["0.00"]}</#if>
                            </div>
                            <form action="/provider/services/${svc.id()?c}/delete" method="POST" onsubmit="return confirm('Remove this service?');">
                                <button class="svc-btn" type="submit">Remove</button>
                            </form>
                        </div>
                        <details>
                            <summary>Edit this service</summary>
                            <form action="/provider/services/${svc.id()?c}" method="POST">
                                <div class="form-group">
                                    <label for="name-${svc.id()?c}">Service Name</label>
                                    <input type="text" id="name-${svc.id()?c}" name="name" value="${svc.name()}" required>
                                </div>
                                <div class="form-group">
                                    <label for="desc-${svc.id()?c}">Description</label>
                                    <textarea id="desc-${svc.id()?c}" name="description">${svc.description()!''}</textarea>
                                </div>
                                <div class="svc-row">
                                    <div class="form-group">
                                        <label for="price-${svc.id()?c}">Price ($)</label>
                                        <input type="number" id="price-${svc.id()?c}" name="price" min="0" step="0.01" value="<#if svc.price()??>${svc.price()?c}</#if>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="dur-${svc.id()?c}">Duration (minutes)</label>
                                        <input type="number" id="dur-${svc.id()?c}" name="durationMinutes" min="0" step="5" value="<#if svc.durationMinutes()??>${svc.durationMinutes()?c}</#if>" required>
                                    </div>
                                </div>
                                <button class="save-button" type="submit">Save Changes</button>
                            </form>
                        </details>
                    </div>
                </#list>
            <#else>
                <p>You haven't added any services yet. Add your first one below.</p>
            </#if>
        </section>

        <section class="card">
            <h2>Add a New Service</h2>
            <form action="/provider/services" method="POST">
                <div class="form-group">
                    <label for="serviceName">Service Name</label>
                    <input type="text" id="serviceName" name="name" placeholder="e.g. Balayage" required>
                </div>
                <div class="form-group">
                    <label for="serviceDesc">Description</label>
                    <textarea id="serviceDesc" name="description" placeholder="Briefly describe this service"></textarea>
                </div>
                <div class="svc-row">
                    <div class="form-group">
                        <label for="servicePrice">Price ($)</label>
                        <input type="number" id="servicePrice" name="price" min="0" step="0.01" placeholder="0.00" required>
                    </div>
                    <div class="form-group">
                        <label for="serviceDuration">Duration (minutes)</label>
                        <input type="number" id="serviceDuration" name="durationMinutes" min="0" step="5" placeholder="60" required>
                    </div>
                </div>
                <button class="save-button" type="submit">Add Service</button>
            </form>
        </section>
    </main>
</@layout.page>
