package net.swordie.ms.client.jobs.anima;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import org.graalvm.nativeimage.c.struct.CField;

import java.util.HashMap;
import java.util.Map;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class Lara extends Job {

    public static final int 前往納林 = 160011074;
    public static final int 獨門咒語 = 160001005;
    public static final int 大自然夥伴 = 160010001;

    public static final int 精氣散播 = 162001000;
    public static final int 山不敗 = 162001005;
    public static final int RADIANT_ORB = 152001002;
    public static final int RADIANT_JAVELIN = 152001001;
    public static final int CRYSTALLINE_WINGS = 152001004;
    public static final int CRYSTALLINE_WINGS_FLY = 152001005;
    public static final int LUCENT_BRAND = 152000007;

    public static final int REACTION_DESTRUCTION = 152100001;
    public static final int REACTION_DOMINATION = 152100002;
    public static final int DEPLOY_CRYSTAL = 152101000;
    public static final int REPOSITION_CRYSTAL = 152101003;
    public static final int CRYSTAL_BATTERY = 152100010;
    public static final int VORTEX_OF_LIGHT = 152101006;
    public static final int UMBRAL_BRAND = 152100012;
    public static final int GAUNTLET_FRENZY = 152101007;
    public static final int MACHINA = 152101008;

    public static final int CRYSTAL_BATTERY_II = 152110008;
    public static final int LUCENT_BRAND_II = 152110009;
    public static final int UMBRAL_BRAND_II = 152110010;
    public static final int RESONANCE = 152111007;
    public static final int REACTION_DESTRUCTION_II = 152110001;
    public static final int REACTION_DOMINATION_II = 152110002;
    public static final int RADIANT_JAVELIN_ENHANCED = 152110004;

    public static final int RADIANT_ORB_II = 152120003;
    public static final int CRYSTAL_BATTERY_III = 152120014;
    public static final int LUCENT_BRAND_III = 152120012;
    public static final int UMBRAL_BRAND_III = 152120013;
    public static final int CRYSTAL_SKILL_DEUS = 152121005;
    public static final int HERO_OF_THE_FLORA = 152121009;
    public static final int FLORAN_HERO_WILL = 152121010;
    public static final int FLASH_CRYSTAL_BATTERY = 152121011;
    public static final int RADIANT_JAVELIN_II = 152120001;
    public static final int LONGINUS_SPEAR = 152121004;
    public static final int WINGS_OF_GLORY = 152111003;
    public static final int DEUS_SUB = 152121006;

    public static final int LONGINUS_ZONE = 152121041;
    public static final int DIVINE_WRATH = 152121042;
    public static final int CRYSTALLINE_BULWARK = 152121043;

    // V skills
    public static final int CRYSTAL_IGNITION = 400021061;
    public static final int REFLECTION_SPECTRAL_BLAST = 400021062;
    public static final int TEMPLAR_KNIGHT = 400021063;
    public static final int CRYSTALLINE_SPIRIT = 400021068;

    public static final int CRYSTAL_SKILL_ID_VORTEX_OF_LIGHT = 1;
    public static final int CRYSTAL_SKILL_ID_RESONANCE = 2;
    public static final int CRYSTAL_SKILL_ID_DEUS = 3;
    public static final int CRYSTAL_SKILL_ID_WINGS_OF_GLORY = 4;
    public static final int CRYSTAL_SKILL_ID_VORTEX_WINGS = 5;

    private static final int[] lucentSkills = new int[]{
            LUCENT_BRAND,
            LUCENT_BRAND_II,
            LUCENT_BRAND_III
    };

    private static final int[] umbralSkills = new int[]{
            UMBRAL_BRAND,
            UMBRAL_BRAND_II,
            UMBRAL_BRAND_III
    };

    public Map<Integer, Boolean> crystalSkillMap = new HashMap<Integer, Boolean>() {{
        put(CRYSTAL_SKILL_ID_VORTEX_OF_LIGHT, true);
        put(CRYSTAL_SKILL_ID_RESONANCE, true);
        put(CRYSTAL_SKILL_ID_DEUS, true);
        put(CRYSTAL_SKILL_ID_WINGS_OF_GLORY, true);
        put(CRYSTAL_SKILL_ID_VORTEX_WINGS, true);
    }};

    private static final int[] addedSkills = new int[]{
            前往納林,
            獨門咒語,
    };

    public Lara(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isLara(id);
    }


    public Summon getCrystal() {
        return chr.getField().getSummons().stream().filter(s -> s.getSkillID() == DEPLOY_CRYSTAL && s.getChr() == chr).findAny().orElse(null);
    }

    private int getCrystalCharge() {
        if (getCrystal() == null) {
            return 0;
        }
        return getCrystal().getCount();
    }

    private void setCrystalCharge(int charge) {
        if (getCrystal() != null) {
            getCrystal().setCount(charge);
        }
    }

    private void resetCrystalBattery() {
        crystalSkillMap.put(CRYSTAL_SKILL_ID_VORTEX_OF_LIGHT, true);
        crystalSkillMap.put(CRYSTAL_SKILL_ID_RESONANCE, true);
        crystalSkillMap.put(CRYSTAL_SKILL_ID_DEUS, true);
        crystalSkillMap.put(CRYSTAL_SKILL_ID_WINGS_OF_GLORY, true);
        chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 2, crystalSkillMap));
        chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
    }

    public void doResonanceSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 1, crystalSkillMap));
        if (!chr.hasSkill(RESONANCE)) {
            return;
        }
        Skill skill = chr.getSkill(RESONANCE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        o.nOption = 1;
        o.rOption = skill.getSkillId();
        o.tOption = si.getValue(x, slv);
        tsm.putCharacterStatValue(Resonance, o);
        tsm.sendSetStatPacket();
        crystalSkillMap.put(CRYSTAL_SKILL_ID_RESONANCE, false);
        chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 2, crystalSkillMap));
        chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
    }

    public void incrementCrystal(int skillId) {
        int increment = (getIncrementBySkillId(skillId) * (hasFlashCrystalBattery() ? 2 : 1));
        changeCrystalCharge(getCrystalCharge() + increment);
        chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks

        if (!chr.hasSkillOnCooldown(REACTION_DESTRUCTION_II) && (skillId == RADIANT_JAVELIN || skillId == RADIANT_JAVELIN_II || skillId == LONGINUS_SPEAR)) {
            doDestructionII();
        }
    }

    private void doDestructionII() {
        SkillInfo si = SkillData.getSkillInfoById(REACTION_DESTRUCTION_II);
        int slv = chr.getSkillLevel(REACTION_DESTRUCTION_II);
        chr.write(Summoned.summonUseSpecifiedSkill(getCrystal(), REACTION_DESTRUCTION_II));
        chr.addSkillCoolTime(REACTION_DESTRUCTION_II, si.getValue(cooltime, slv) * 1000);
    }

    private void changeCrystalCharge(int charge) {
        int curState = getCrystal().getState();

        charge = charge < 0 ? 0 : charge > getMaxCrystalCharge() ? getMaxCrystalCharge() : charge;
        setCrystalCharge(charge);
        getCrystal().setState(getCrystalStateByCharge(charge));
        chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 2)); // change Crystal Summon State

        if (curState != getCrystal().getState()) {
            chr.getField().broadcastPacket(Summoned.effect(getCrystal(), 3)); // new form effect
        } else {
            chr.getField().broadcastPacket(Summoned.effect(getCrystal(), 2)); // increment effect
        }

        if (getCrystalCharge() >= getMaxCrystalCharge() && !chr.getTemporaryStatManager().hasStat(CrystalBattery)) {
            giveCrystalBatteryBuff();
        }
    }

    private int getMaxCrystalCharge() {
        int maxCrystalCharge = 0;
        if (chr.hasSkill(CRYSTAL_BATTERY)) {
            maxCrystalCharge = 30;
        }
        if (chr.hasSkill(CRYSTAL_BATTERY_II)) {
            maxCrystalCharge = 150;
        }
        return maxCrystalCharge;
    }

    private Skill getCrystalBatterySkill() {
        Skill skill = null;
        if (chr.hasSkill(CRYSTAL_BATTERY)) {
            skill = chr.getSkill(CRYSTAL_BATTERY);
        }
        if (chr.hasSkill(CRYSTAL_BATTERY_II)) {
            skill = chr.getSkill(CRYSTAL_BATTERY_II);
        }
        if (chr.hasSkill(CRYSTAL_BATTERY_III)) {
            skill = chr.getSkill(CRYSTAL_BATTERY_III);
        }
        return skill;
    }

    private boolean hasFlashCrystalBattery() {
        return chr.getTemporaryStatManager().hasStat(FlashCrystalBattery);
    }

    private void giveCrystalBatteryBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        o.rOption = getCrystalBatterySkill().getSkillId();
        o.tOption = 10;
        tsm.putCharacterStatValue(CrystalBattery, o);
        tsm.sendSetStatPacket();
    }

    private int getIncrementBySkillId(int skillId) {
        switch (skillId) {
            case RADIANT_JAVELIN:
            case RADIANT_JAVELIN_II:
                return 1;
            case RADIANT_ORB:
            case RADIANT_ORB_II:
                return 2;
            case LONGINUS_SPEAR:
                return 3;
        }
        return 0;
    }

    private int getCrystalStateByCharge(int count) {
        int state = 0;
        if (count >= 0) {
            state = 0;
        }
        if (count >= 30) {
            state = 1;
        }
        if (count >= 60) {
            state = 2;
        }
        if (count >= 100) {
            state = 3;
        }
        if (count >= 150) {
            state = 4;
        }
        return state;
    }

    public void handleNatureFriend() {
        final int skilllv = (chr.getSkillLevel(80003070) > 0) ? chr.getSkillLevel(80003070) : chr.getSkillLevel(160010001);
        final int skillId = (chr.getSkillLevel(80003070) > 0) ? 80003070 : 160010001;
        if (chr.hasSkillOnCooldown(skillId)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(skillId);
        SkillInfo si = null;
        Option o1 = new Option();
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
        }
        if (!tsm.hasStat(CharacterTemporaryStat.大自然夥伴)) {
            chr.setSkillCustomInfo(80003070, 0L, 0L);
        } else {
            chr.addSkillCustomInfo(80003070, 1L);
        }
        if (chr.getSkillCustomValue0(80003070) >= si.getValue(x, skilllv)) {
            chr.addSkillCoolTime(skillId, System.currentTimeMillis());
            chr.write(UserLocal.skillCooltimeSetM(skillId, si.getValue(cooltime, skilllv)));
            tsm.removeStatsBySkill(80003070);
        } else {
            o1.nOption = 5;
            o1.tOption = si.getValue(time, skilllv);
            tsm.putCharacterStatValue(CharacterTemporaryStat.大自然夥伴, o1);
            tsm.sendSetStatPacket();
        }
    }


    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        if (hasHitMobs
                && attackInfo.skillId != CONVERSION_OVERDRIVE_ATTACK) {
            bonusConversionOverdriveAttack();
        }
        switch (attackInfo.skillId) {         
            case DEPLOY_CRYSTAL:
                int attackingSkillId = attackInfo.summonSpecialSkillId;
                switch (attackingSkillId) {
                    case REACTION_DOMINATION:
                    case REACTION_DESTRUCTION:
                    case REACTION_DESTRUCTION_II:    
                    case REACTION_DOMINATION_II:
                        slv = chr.getSkillLevel(attackingSkillId);
                        chr.setSkillCooldown(attackingSkillId, slv);
                        break;
                    case VORTEX_OF_LIGHT:
                        crystalSkillMap.put(CRYSTAL_SKILL_ID_VORTEX_OF_LIGHT, false);
                        chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 2, crystalSkillMap));
                        break;
                    case REFLECTION_SPECTRAL_BLAST:
                        chr.setSkillCooldown(attackingSkillId, chr.getSkillLevel(CRYSTAL_IGNITION));
                        break;             
                }
                chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillID, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Summon summon;
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case 山不敗:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(CharacterTemporaryStat.山不敗, o1);
                tsm.sendSetStatPacket();
                break;
        }
        if (chr.getSkillLevel(160010001) > 0 || chr.getSkillLevel(80003058) > 0) {
            handleNatureFriend();
        }

    }

    public void handleShootObj(Char chr, int skillId, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case RADIANT_ORB:
            case RADIANT_ORB_II:
                SkillInfo si = SkillData.getSkillInfoById(skillId);
                slv = chr.getSkillLevel(skillId);
                giveLucentBrand();
                Option o = new Option();
                Option o1 = new Option();
                o.nOption = 1;
                o.rOption = skillId;
                o.tOption = 2;
                o.setInMillis(true);
                //tsm.putCharacterStatValue(RadiantOrb, o);
//                o1.nOption = si.getValue(y, slv);
//                o1.rOption = skillId;
//                o1.tOption = 2;
//                o.setInMillis(true);
//                tsm.putCharacterStatValue(NuclearOption, o1); // NuclearOption is moved/removed
                tsm.sendSetStatPacket();
                break;
        }
        super.handleShootObj(chr, skillId, slv);
    }

    private void giveLucentBrand() {
        Skill skill = getLucentBrandSkill();
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        int stack = 1;
        if (tsm.hasStat(LucentBrand)) {
            stack = tsm.getOption(LucentBrand).nOption;
            if (stack < getMaxLucentBrand()) {
                stack++;
            }
        }
        o1.nOption = stack;
        o1.rOption = skill.getSkillId();
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(LucentBrand, o1);
        o2.nReason = skill.getSkillId();
        o2.nValue = si.getValue(w, slv) * stack;
        o2.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndiePAD, o2);
        tsm.putCharacterStatValue(IndieMAD, o2);
        tsm.sendSetStatPacket();
    }

    private int getMaxLucentBrand() {
        int maxStack = 0;
        for (int lucentSkillId : lucentSkills) {
            if (chr.hasSkill(lucentSkillId)) {
                SkillInfo si = SkillData.getSkillInfoById(lucentSkillId);
                maxStack = si.getValue(x, chr.getSkillLevel(lucentSkillId));
            }
        }
        return maxStack;
    }

    private Skill getLucentBrandSkill() {
        Skill skill = null;
        for (int lucentSkillId : lucentSkills) {
            if (chr.hasSkill(lucentSkillId)) {
                skill = chr.getSkill(lucentSkillId);
            }
        }
        return skill;
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        if (cts == CrystalBattery) {
            changeCrystalCharge(0);
            resetCrystalBattery();
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        //cs.setPosMap(100000000); // default - 940202013
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        int level = chr.getLevel();
        switch (level) {
            case 30:
                handleJobAdvance(JobConstants.JobEnum.LARA_2.getJobId());
                chr.addSpToJobByCurrentJob(7);
                break;
            case 60:
                handleJobAdvance(JobConstants.JobEnum.LARA_3.getJobId());
                chr.addSpToJobByCurrentJob(7);
                break;
            case 100:
                handleJobAdvance(JobConstants.JobEnum.LARA_4.getJobId());
                chr.addSpToJobByCurrentJob(5);
                break;
        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.LARA_1.getJobId());

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();
        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1354020)); // 樸素的四玉飾品
        chr.addSpToJobByCurrentJob(8);
    }
}
